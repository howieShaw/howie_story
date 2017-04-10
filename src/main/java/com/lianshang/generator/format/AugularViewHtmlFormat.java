package com.lianshang.generator.format;

import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.ColumnMeta;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.Tools;

/**
 * Created by walker on 16/2/16.
 */
public class AugularViewHtmlFormat {

    public static String getFileContent(String className, String prefixClassPackage, TableMeta meta) throws ServiceException {
       /* String tableName = Tools.lineToHump(meta.getTableName());
        String upTableName = Tools.upCaptureName(tableName);
        StringBuilder builder = new StringBuilder();
        builder.append(
            ""
        );

        return builder.toString();*/
        return AugularEditHtmlFormat.getFileContent( className,  prefixClassPackage,  meta);
    }
}
