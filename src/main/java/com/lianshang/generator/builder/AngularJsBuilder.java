package com.lianshang.generator.builder;

import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.format.AugularListHtmlFormat;
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

        String className = NameUtil.getHtmlName(meta);

        String fileContent = AugularListHtmlFormat
            .getFileContent(className, mc.getPrefixClassPackage(), meta);

        String filePath = dc.getAngularHtmlPath()+ className ;

        boolean result = FileUtil.writeFile(filePath, fileContent);
        assert result;


    }


}
