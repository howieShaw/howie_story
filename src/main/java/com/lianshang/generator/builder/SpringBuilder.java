package com.lianshang.generator.builder;

import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.DBUtil;
import com.lianshang.generator.util.FileUtil;
import com.lianshang.generator.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by walker on 16/2/16.
 */
public class SpringBuilder {

    public static void build(ModuleConfig mc, DirectoryConfig dc, List<TableMeta> tableMetaList) {

        String basicPath = StringUtil.getResourceBasicPath();

        String baseConfig = FileUtil.loadFromFile(basicPath + "/spring/base-config.xml");

        String springSaveDir = dc.getBizResourcePrefixPath() + "/config/spring";
        FileUtil.makeDir(springSaveDir);

        buildClientConfig(springSaveDir, baseConfig);

        buildCoreConfig(springSaveDir, baseConfig);

        buildDbConfig(springSaveDir, baseConfig, mc);

        buildIbatisConfig(springSaveDir, baseConfig, mc);

        buildDaoConfig(springSaveDir, baseConfig, mc, tableMetaList);

        buildServiceConfig(springSaveDir, baseConfig, mc, tableMetaList);
    }

    private static void buildClientConfig(String springSaveDir, String baseConfig) {

        String content = Constant.TAB + "<bean name=\"placeholder\" lazy-init=\"false\" class=\"com.dianping.lion.client.InitializeConfig\">" + Constant.RETURN +
                Constant.TAB + "</bean>" + Constant.RETURN;

        buildConfig(springSaveDir + "/appcontext-client.xml", content, baseConfig);
    }

    private static void buildCoreConfig(String springSaveDir, String baseConfig) {

        String content = Constant.TAB + "<context:annotation-config />" + Constant.RETURN;

        buildConfig(springSaveDir + "/appcontext-core.xml", content, baseConfig);
    }

    private static void buildDbConfig(String springSaveDir, String baseConfig, ModuleConfig mc) {

        String content;
        Map<String, String> replaceParams = new HashMap<>();

        if (mc.isDependDp()) {

            content = Constant.TAB + "<bean id=\"{MODULE}DataSource\" class=\"com.dianping.zebra.group.jdbc.GroupDataSource\" init-method=\"init\">" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"jdbcRef\" value=\"{MODULE}\" />" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"routerType\" value=\"fail-over\" />" + Constant.RETURN +
                    Constant.TAB + "</bean>" + Constant.RETURN;

            replaceParams.put("[{]MODULE[}]", mc.getModuleName());
        }
        else {
            content = Constant.TAB + "<bean id=\"{MODULE}DataSource\" class=\"com.mchange.v2.c3p0.ComboPooledDataSource\">" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"driverClass\" value=\"com.mysql.jdbc.Driver\"></property>" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"jdbcUrl\" value=\"{DBConnectionStr}\"></property>" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"user\" value=\"{DBUserName}\"></property>" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"password\" value=\"{DBPassword}\"></property>" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"maxPoolSize\" value=\"10\"></property>" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"maxIdleTime\" value=\"7200\"></property>" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"testConnectionOnCheckin\" value=\"true\"></property>" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"idleConnectionTestPeriod\" value=\"5\"></property>" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"preferredTestQuery\" value=\"SELECT 1\"></property>" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"checkoutTimeout\" value=\"1800000\"></property>" + Constant.RETURN +
                    Constant.TAB + "</bean>" + Constant.RETURN;

            replaceParams.put("[{]MODULE[}]", mc.getModuleName());
            replaceParams.put("[{]DBConnectionStr[}]", DBUtil.getConnectionUrl(mc.getDbConfig()));
            replaceParams.put("[{]DBUserName[}]", mc.getDbConfig().getUsername());
            replaceParams.put("[{]DBPassword[}]", mc.getDbConfig().getPassword());
        }

        buildConfig(springSaveDir + "/appcontext-db.xml", content, baseConfig, replaceParams);
    }

    private static void buildIbatisConfig(String springSaveDir, String baseConfig, ModuleConfig mc) {

        String content = Constant.TAB + "<bean id=\"{MODULE}SqlSessionFactory\" class=\"org.mybatis.spring.SqlSessionFactoryBean\">" + Constant.RETURN +
                Constant.TAB2 + "<property name=\"dataSource\" ref=\"{MODULE}DataSource\" />\n" +
                Constant.TAB2 + "<property name=\"mapperLocations\" value=\"classpath*:config/sqlmap/{MODULE}/*.xml\" />" + Constant.RETURN +
                Constant.TAB2 + "<property name=\"typeAliasesPackage\" value=\"{PACKAGE}.biz.entity\" />" + Constant.RETURN +
                Constant.TAB + "</bean>" + Constant.RETURN;

        Map<String, String> replaceParams = new HashMap<>();
        replaceParams.put("[{]MODULE[}]", mc.getModuleName());
        replaceParams.put("[{]PACKAGE[}]", mc.getPrefixClassPackage());

        buildConfig(springSaveDir + "/appcontext-ibatis.xml", content, baseConfig, replaceParams);
    }

    private static void buildDaoConfig(String springSaveDir
            , String baseConfig
            , ModuleConfig mc
            , List<TableMeta> tableMetaList) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tableMetaList.size(); ++i) {

            if (i != 0) {
                builder.append(Constant.RETURN);
            }

            TableMeta meta = tableMetaList.get(i);

            String daoInterfaceName = NameUtil.getDaoInterfaceName(meta);
            String daoVarName = StringUtil.toLowerForFirstChar(daoInterfaceName);

            String daoContent = Constant.TAB + "<bean id=\"{DAO-VAR-NAME}\" class=\"org.mybatis.spring.mapper.MapperFactoryBean\" >" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"mapperInterface\" value=\"{PACKAGE}.biz.dao.{DAO-INTERFACE-NAME}\" />" + Constant.RETURN +
                    Constant.TAB2 + "<property name=\"sqlSessionFactory\" ref=\"{MODULE}SqlSessionFactory\" />" + Constant.RETURN +
                    Constant.TAB + "</bean>" + Constant.RETURN;

            Map<String, String> replaceParams = new HashMap<>();
            replaceParams.put("[{]MODULE[}]", mc.getModuleName());
            replaceParams.put("[{]PACKAGE[}]", mc.getPrefixClassPackage());
            replaceParams.put("[{]DAO-VAR-NAME[}]", daoVarName);
            replaceParams.put("[{]DAO-INTERFACE-NAME[}]", daoInterfaceName);

            builder.append(StringUtil.replace(daoContent, replaceParams));
        }

        buildConfig(springSaveDir + "/appcontext-dao.xml", builder.toString(), baseConfig);
    }

    private static void buildServiceConfig(String springSaveDir
            , String baseConfig
            , ModuleConfig mc
            , List<TableMeta> tableMetaList) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tableMetaList.size(); ++i) {

            if (i != 0) {
                builder.append(Constant.RETURN);
            }

            TableMeta meta = tableMetaList.get(i);

            String serviceVarName = StringUtil.toLowerForFirstChar(NameUtil.getServiceInterfaceName(meta));
            String serviceImplClassName = NameUtil.getServiceImplClassName(meta);

            String daoContent = Constant.TAB + "<bean id=\"{SERVICE-VAR-NAME}\" class=\"{PACKAGE}.biz.service.impl.{SERVICE-IMPL-CLASS-NAME}\" />" + Constant.RETURN;

            Map<String, String> replaceParams = new HashMap<>();
            replaceParams.put("[{]MODULE[}]", mc.getModuleName());
            replaceParams.put("[{]PACKAGE[}]", mc.getPrefixClassPackage());
            replaceParams.put("[{]SERVICE-VAR-NAME[}]", serviceVarName);
            replaceParams.put("[{]SERVICE-IMPL-CLASS-NAME[}]", serviceImplClassName);

            builder.append(StringUtil.replace(daoContent, replaceParams));
        }

        buildConfig(springSaveDir + "/appcontext-service2.xml", builder.toString(), baseConfig);
    }

    private static void buildConfig(String path, String content, String baseConfig) {

        Map<String, String> replaceParams = new HashMap<>();
        replaceParams.put("[{]CONTENT[}]", content);

        boolean result = FileUtil.writeFile(path, StringUtil.replace(baseConfig, replaceParams));
        assert result;
    }

    private static void buildConfig(String path, String content, String baseConfig, Map<String, String> replaceParams) {

        replaceParams.put("[{]CONTENT[}]", content);

        boolean result = FileUtil.writeFile(path, StringUtil.replace(baseConfig, replaceParams));
        assert result;
    }
}
