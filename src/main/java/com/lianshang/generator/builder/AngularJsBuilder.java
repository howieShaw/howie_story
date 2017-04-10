package com.lianshang.generator.builder;

import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.format.AugularAddHtmlFormat;
import com.lianshang.generator.format.AugularConfirmDeleteHtmlFormat;
import com.lianshang.generator.format.AugularEditHtmlFormat;
import com.lianshang.generator.format.AugularListControllerFormat;
import com.lianshang.generator.format.AugularListHtmlFormat;
import com.lianshang.generator.format.AugularListModuleFormat;
import com.lianshang.generator.format.AugularListServiceFormat;
import com.lianshang.generator.format.AugularViewHtmlFormat;
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
//==================列表html======================================================
        String fileContent = AugularListHtmlFormat
            .getFileContent(classHtmlName, mc.getPrefixClassPackage(), meta);

        String filePath = tableapath+ classHtmlName ;

        boolean result = FileUtil.writeFile(filePath, fileContent);
        assert result;
//========================列表controller=======================================================
        String classControllerName = NameUtil.getAugularControllerName(meta);

        String fileControllerContent = AugularListControllerFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta,tableapath,tablemodelpath);
        String fileeControllerPath = tableapath+ classControllerName ;

        result = FileUtil.writeFile(fileeControllerPath,fileControllerContent);
        assert result;
//========================列表module=====================================================
        String MoudleName = className+".module.js";

        String fileMoudleContent = AugularListModuleFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String fileMoudlePath = tableapath+ MoudleName ;

        result = FileUtil.writeFile(fileMoudlePath,fileMoudleContent);
        assert result;

        //=======================列表service=======================================================
        String ServiceName = className+".service.js";

        String fileServiceContent = AugularListServiceFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String fileServicePath = tableapath+ ServiceName ;

        result = FileUtil.writeFile(fileServicePath,fileServiceContent);
        assert result;

        //=====================新增html============================================================
        String addhtml = "add-"+className+".html";

        String addhtmlContent = AugularAddHtmlFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String addhtmlPath = tablemodelpath+ "/"+addhtml ;

        result = FileUtil.writeFile(addhtmlPath,addhtmlContent);
        assert result;

        //=====================修改html============================================================
        String edithtml = "edit-"+className+".html";

        String edithtmlContent = AugularEditHtmlFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String edithtmlPath = tablemodelpath+ "/"+edithtml ;

        result = FileUtil.writeFile(edithtmlPath,edithtmlContent);
        assert result;

        //=====================查看html============================================================
        String viewhtml = "view-"+className+".html";

        String viewhtmlContent = AugularViewHtmlFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String viewhtmlPath = tablemodelpath+ "/"+viewhtml ;

        result = FileUtil.writeFile(viewhtmlPath,viewhtmlContent);
        assert result;

        //=====================删除html============================================================
        String deletehtml = "confirmDelete-"+className+".html";

        String deletehtmlContent = AugularConfirmDeleteHtmlFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);
        String deletehtmlPath = tablemodelpath+ "/"+deletehtml ;

        result = FileUtil.writeFile(deletehtmlPath,deletehtmlContent);
        assert result;
    }


}
