package com.lianshang.generator.format;

import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.TableMeta;
import java.util.Random;

/**
 * Created by walker on 16/2/16.
 */
public class AugularFormat  {

    public static String getFileContent(String className, String prefixClassPackage, TableMeta meta) throws ServiceException {

        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"wrapper wrapper-content animated fadeInRight ng-scope\">");
        builder.append(Constant.RETURN);

        return builder.toString();

    }
}
