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
        String tableapath = dc.getAngularHtmlPath() +"/"+className+"/";
        FileUtil.makeDir(tableapath);
        String tablemodelpath = dc.getAngularHtmlPath() +"/"+className+"/"+"model";
        FileUtil.makeDir(tablemodelpath);

        String fileContent = AugularListHtmlFormat
            .getFileContent(classHtmlName, mc.getPrefixClassPackage(), meta);

        String filePath = tableapath+ classHtmlName ;

        boolean result = FileUtil.writeFile(filePath, fileContent);
        assert result;
//=============================================================================
        String classControllerName = NameUtil.getAugularControllerName(meta);

        String fileControllerContent = AugularListControllerFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String fileeControllerPath = tableapath+ classControllerName ;

        result = FileUtil.writeFile(fileeControllerPath,fileControllerContent);
        assert result;
//=============================================================================
        String MoudleName = className+".module.js";

        String fileMoudleContent = AugularListModuleFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String fileMoudlePath = tableapath+ MoudleName ;

        result = FileUtil.writeFile(fileMoudlePath,fileMoudleContent);
        assert result;

        //=============================================================================
        String ServiceName = className+".service.js";

        String fileServiceContent = AugularListServiceFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String fileServicePath = tableapath+ ServiceName ;

        result = FileUtil.writeFile(fileServicePath,fileServiceContent);
        assert result;
    }


}
