package com.lianshang.generator.builder;

import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.format.*;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.FileUtil;

/**
 * Created by walker on 16/2/16.
 */
public class TableSourceBuilder {

    public static void build(ModuleConfig mc, DirectoryConfig dc, TableMeta meta) throws ServiceException{

        buildDtoFile(mc, dc, meta);

        buildEntityFile(mc, dc, meta);

        buildServiceFile(mc, dc, meta);

        buildDaoFile(mc, dc, meta);

        buildServiceImplFile(mc, dc, meta);
    }

    private static void buildDtoFile(ModuleConfig mc, DirectoryConfig dc, TableMeta meta) throws ServiceException{

        String className = NameUtil.getDtoClassName(mc.isNeedDtoSubfix(), meta);

        String fileContent = DtoFormat.getFileContent(className, mc.getPrefixClassPackage(), meta);

        String filePath = dc.getApiSourcePrefixPath() + "/dto/" + className + ".java";

        boolean result = FileUtil.writeFile(filePath, fileContent);
        assert result;
    }

    private static void buildEntityFile(ModuleConfig mc, DirectoryConfig dc, TableMeta meta) throws ServiceException{

        String className = NameUtil.getEntityClassName(meta);

        String fileContent = EntityFormat.getFileContent(className, mc.getPrefixClassPackage(), meta);

        String filePath = dc.getBizSourcePrefixPath() + "/entity/" + className + ".java";

        boolean result = FileUtil.writeFile(filePath, fileContent);
        assert result;
    }

    private static void buildServiceFile(ModuleConfig mc, DirectoryConfig dc, TableMeta meta) throws ServiceException{

        String interfaceName = NameUtil.getServiceInterfaceName(meta);
        String dtoClassName = NameUtil.getDtoClassName(mc.isNeedDtoSubfix(), meta);

        String fileContent = ServiceFormat.getFileContent(interfaceName, dtoClassName, mc.getPrefixClassPackage(), meta);

        String filePath = dc.getApiSourcePrefixPath() + "/service/" + interfaceName + ".java";

        boolean result = FileUtil.writeFile(filePath, fileContent);
        assert result;
    }

    private static void buildDaoFile(ModuleConfig mc, DirectoryConfig dc, TableMeta meta) throws ServiceException{

        String interfaceName = NameUtil.getDaoInterfaceName(meta);
        String entityClassName = NameUtil.getEntityClassName(meta);

        String fileContent = DaoFormat.getFileContent(interfaceName, entityClassName, mc.getPrefixClassPackage(), meta);

        String filePath = dc.getBizSourcePrefixPath() + "/dao/" + interfaceName + ".java";

        boolean result = FileUtil.writeFile(filePath, fileContent);
        assert result;
    }

    private static void buildServiceImplFile(ModuleConfig mc, DirectoryConfig dc, TableMeta meta) throws ServiceException{

        String serviceInterfaceName = NameUtil.getServiceInterfaceName(meta);
        String serviceImplClassName = NameUtil.getServiceImplClassName(meta);
        String daoInterfaceName = NameUtil.getDaoInterfaceName(meta);
        String dtoClassName = NameUtil.getDtoClassName(mc.isNeedDtoSubfix(), meta);
        String entityClassName = NameUtil.getEntityClassName(meta);

        String fileContent = ServiceImplFormat.getFileContent(serviceImplClassName
                , serviceInterfaceName
                , dtoClassName
                , entityClassName
                , daoInterfaceName
                , mc.getPrefixClassPackage()
                , meta);

        String filePath = dc.getBizSourcePrefixPath() + "/service/impl/" + serviceImplClassName + ".java";

        boolean result = FileUtil.writeFile(filePath, fileContent);
        assert result;
    }

}
