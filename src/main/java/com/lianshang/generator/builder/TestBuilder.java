package com.lianshang.generator.builder;

import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.format.DaoTestFormat;
import com.lianshang.generator.format.ServiceTestFormat;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.FileUtil;
import com.lianshang.generator.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by walker on 16/2/17.
 */
public class TestBuilder {

    public static void build(ModuleConfig mc, DirectoryConfig dc, List<TableMeta> tableMetaList) throws ServiceException {

        FileUtil.makeDir(dc.getBizTestSourcePrefixPath() + "/service");
        FileUtil.makeDir(dc.getBizTestSourcePrefixPath() + "/dao");

        for (TableMeta meta : tableMetaList) {

            buildServiceTest(mc, dc, meta);
            buildDaoTest(mc, dc, meta);
        }
    }

    public static void buildResource(ModuleConfig mc, DirectoryConfig dc, List<TableMeta> tableMetaList) {
        buildDaoResourceTest(mc, dc, tableMetaList);
        buildDdlTest(dc, tableMetaList);
    }

    private static void buildServiceTest(ModuleConfig mc, DirectoryConfig dc, TableMeta meta) throws ServiceException{

        String testClassName = NameUtil.getServiceTestClassName(meta);
        String dtoClassName = NameUtil.getDtoClassName(mc.isNeedDtoSubfix(), meta);
        String implClassName = NameUtil.getServiceImplClassName(meta);
        String daoClassName = NameUtil.getDaoInterfaceName(meta);

        String fileContent = ServiceTestFormat.getFileContent(testClassName, daoClassName, implClassName, dtoClassName, mc.getPrefixClassPackage(), meta);

        String filePath = dc.getBizTestSourcePrefixPath() + "/service/" + testClassName + ".java";

        boolean result = FileUtil.writeFile(filePath, fileContent);
        assert result;
    }

    private static void buildDaoTest(ModuleConfig mc, DirectoryConfig dc, TableMeta meta) throws ServiceException {
        String daoClassName = NameUtil.getDaoInterfaceName(meta);
        String entityName = NameUtil.getEntityClassName(meta);
        String daoTestClassName = NameUtil.getDaoTestClassName(meta);

        String daoFileContent = DaoTestFormat.getFileContent(daoClassName, entityName, mc.getPrefixClassPackage(), meta);

        String filePath = dc.getBizTestSourcePrefixPath() + "/dao/" + daoTestClassName + ".java";

        boolean result = FileUtil.writeFile(filePath, daoFileContent);
        assert result;
    }

    private static void buildDaoResourceTest(ModuleConfig mc, DirectoryConfig dc, List<TableMeta> tableMetaList) {
        String basicPath = StringUtil.getResourceBasicPath();
        String baseConfig = FileUtil.loadFromFile(basicPath + "/spring/h2-dao-config.xml");
        String saveToDir =  dc.getBizTestResourcePrefixPath() + "/mock/spring";

        FileUtil.makeDir(saveToDir);

        String saveToFile = saveToDir + "/h2-dao.xml";
        baseConfig = baseConfig.replace("{CONTENT}", buildDaoResourceContent(mc));
        boolean result = FileUtil.writeFile(saveToFile, baseConfig);

        assert result;
    }

    private static String buildDaoResourceContent(ModuleConfig mc) {
        String daoPackage = mc.getPrefixClassPackage() + ".biz.dao";

        StringBuilder sb = new StringBuilder();
        sb.append(Constant.TAB + "<bean class=\"org.mybatis.spring.mapper.MapperScannerConfigurer\">" + Constant.RETURN);
        sb.append(Constant.TAB2 + "<property name=\"basePackage\" value=\" " +
                daoPackage + "\"/>" + Constant.RETURN);
        sb.append(Constant.TAB2 + "<property name=\"sqlSessionFactory\" ref=\"sqlSessionFactory\"/>" + Constant.RETURN);
        sb.append(Constant.TAB + "</bean>" + Constant.RETURN);
        sb.append(Constant.RETURN);
        sb.append(Constant.TAB + "<bean id=\"sqlSessionFactory\" class=\"org.mybatis.spring.SqlSessionFactoryBean\">" + Constant.RETURN);
        sb.append(Constant.TAB2 + "<property name=\"dataSource\" ref=\"dataSource\"/>" + Constant.RETURN);
        sb.append(Constant.TAB2 + "<property name=\"mapperLocations\">" + Constant.RETURN);
        sb.append(Constant.TAB3 + "<array>" + Constant.RETURN);
        sb.append(Constant.TAB4 + "<value>classpath:/config/sqlmap/" + mc.getModuleName() + "/*.xml</value>" + Constant.RETURN);
        sb.append(Constant.TAB3 + "</array>" + Constant.RETURN);
        sb.append(Constant.TAB2 + "</property>" + Constant.RETURN);
        sb.append(Constant.TAB2 + "<property name=\"typeAliasesPackage\" value=\" "
                + mc.getPrefixClassPackage() + ".biz.entity" + "\"/>" + Constant.RETURN);
        sb.append(Constant.TAB + "</bean>" + Constant.RETURN);
        sb.append(Constant.RETURN);
        sb.append(Constant.TAB + "<bean id=\"dataSource\" " +
                "class=\"org.springframework.jdbc.datasource.DriverManagerDataSource\">" + Constant.RETURN);
        sb.append(Constant.TAB2 + "<property name=\"driverClassName\" value=\"org.h2.Driver\"/>" + Constant.RETURN);
        sb.append(Constant.TAB2 + "<property name=\"url\" value=\"jdbc:h2:mem:bone;DB_CLOSE_DELAY=-1;MODE=MySQL\"/>" + Constant.RETURN);
        sb.append(Constant.TAB + "</bean>" + Constant.RETURN);
        sb.append(Constant.RETURN);
        sb.append(Constant.TAB + "<jdbc:initialize-database data-source=\"dataSource\" ignore-failures=\"DROPS\">" + Constant.RETURN);
        sb.append(Constant.TAB2 + "<jdbc:script location=\"classpath:mock/sql/ddl.sql\"/>" + Constant.RETURN);
        sb.append(Constant.TAB + "</jdbc:initialize-database>" + Constant.RETURN);
        sb.append(Constant.RETURN);
        sb.append(Constant.TAB + "<tx:annotation-driven transaction-manager=\"transactionManager\" proxy-target-class=\"true\"/>" + Constant.RETURN);
        sb.append(Constant.TAB + "<bean id=\"transactionManager\" " +
                "class=\"org.springframework.jdbc.datasource.DataSourceTransactionManager\">" + Constant.RETURN);
        sb.append(Constant.TAB2 + "<property name=\"dataSource\" ref=\"dataSource\"/>" + Constant.RETURN);
        sb.append(Constant.TAB + "</bean>" + Constant.RETURN);

        return sb.toString();
    }

    private static void buildDdlTest(DirectoryConfig dc, List<TableMeta> tableMetaList) {
        String saveToDir = dc.getBizTestResourcePrefixPath() + "/mock/sql";

        FileUtil.makeDir(saveToDir);
        boolean result = false;

        for (TableMeta tableMeta: tableMetaList) {
            String saveToFile = saveToDir  + "/" + tableMeta.getTableName() + "ddl.sql";

            StringBuilder sb = new StringBuilder();
            sb.append(tableMeta.getCreateTableSql() + ";" + Constant.RETURN);
            sb.append(Constant.RETURN);

            result = FileUtil.writeFile(saveToFile, sb.toString());
        }


        assert  result;
    }
}
