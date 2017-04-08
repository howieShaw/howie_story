package com.lianshang.generator.builder;

import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.format.AugularListControllerFormat;
import com.lianshang.generator.format.AugularListHtmlFormat;
import com.lianshang.generator.format.AugularListModuleFormat;
import com.lianshang.generator.format.AugularListServiceFormat;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.FileUtil;
import java.util.List;

/**
 * Created by walker on 16/2/16.
 */
public class AngularJsBuilder {

    public static void build(ModuleConfig mc, DirectoryConfig dc,List<TableMeta> tableMetaList) throws ServiceException{

        //buildAugularFile(mc, dc, meta);
        for (TableMeta meta : tableMetaList) {

            AngularJsBuilder.buildAugularFile(mc, dc, meta);
        }

    }

    private static void buildAugularFile(ModuleConfig mc, DirectoryConfig dc, TableMeta meta) throws ServiceException{
        String className = NameUtil.getClassName(meta);

        String classHtmlName = NameUtil.getHtmlName(meta);

        String fileContent = AugularListHtmlFormat
            .getFileContent(classHtmlName, mc.getPrefixClassPackage(), meta);

        String filePath = dc.getAngularHtmlPath()+ classHtmlName ;

        boolean result = FileUtil.writeFile(filePath, fileContent);
        assert result;
//=============================================================================
        String classControllerName = NameUtil.getAugularControllerName(meta);

        String fileControllerContent = AugularListControllerFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String fileeControllerPath = dc.getAngularHtmlPath()+ classControllerName ;

        result = FileUtil.writeFile(fileeControllerPath,fileControllerContent);
        assert result;
//=============================================================================
        String MoudleName = className+".module.js";

        String fileMoudleContent = AugularListModuleFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String fileMoudlePath = dc.getAngularHtmlPath()+ MoudleName ;

        result = FileUtil.writeFile(fileMoudlePath,fileMoudleContent);
        assert result;

        //=============================================================================
        String ServiceName = className+".service.js";

        String fileServiceContent = AugularListServiceFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String fileServicePath = dc.getAngularHtmlPath()+ ServiceName ;

        result = FileUtil.writeFile(fileServicePath,fileServiceContent);
        assert result;
    }


}
