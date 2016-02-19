package com.lianshang.generator.builder;

import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.exception.ServiceException;
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

        buildBaseTest(mc, dc);

        FileUtil.makeDir(dc.getBizTestSourcePrefixPath() + "/service");

        for (TableMeta meta : tableMetaList) {

            buildServiceTest(mc, dc, meta);
        }
    }

    private static void buildServiceTest(ModuleConfig mc, DirectoryConfig dc, TableMeta meta) throws ServiceException{

        String testClassName = NameUtil.getServiceTestClassName(meta);
        String interfaceName = NameUtil.getServiceInterfaceName(meta);
        String dtoClassName = NameUtil.getDtoClassName(mc.isNeedDtoSubfix(), meta);

        String fileContent = ServiceTestFormat.getFileContent(testClassName, interfaceName, dtoClassName, mc.getPrefixClassPackage(), meta);

        String filePath = dc.getBizTestSourcePrefixPath() + "/service/" + testClassName + ".java";

        boolean result = FileUtil.writeFile(filePath, fileContent);
        assert result;
    }

    private static void buildBaseTest(ModuleConfig mc, DirectoryConfig dc) {

        Map<String, String> replaceParams = new HashMap<>();
        replaceParams.put("[{]PACKAGE[}]", mc.getPrefixClassPackage());

        String loadPath = StringUtil.getResourceBasicPath() + "/source/base-test.java";

        boolean result = FileUtil.writeFile(dc.getBizTestSourcePrefixPath() + "/BaseTest.java"
                , StringUtil.replace(FileUtil.loadFromFile(loadPath), replaceParams));
        assert result;
    }
}
