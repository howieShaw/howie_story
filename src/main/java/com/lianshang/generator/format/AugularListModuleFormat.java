package com.lianshang.generator.format;

import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.Tools;

/**
 * Created by walker on 16/2/16.
 */
public class AugularListModuleFormat {

    public static String getFileContent(String className, String prefixClassPackage, TableMeta meta) throws ServiceException {
        String tableName = Tools.lineToHump(meta.getTableName());

        StringBuilder builder = new StringBuilder();
        builder.append("(function () {\n"
            + "\n"
            + "  'use strict';\n"
            + "\n"
            + "  angular\n"
            + "  .module('app."+className+"-mgmt', [\n"
            + "    'app.core'\n"
            + "  ]);\n"
            + "\n"
            + "})();\n");

        return builder.toString();

    }
}
