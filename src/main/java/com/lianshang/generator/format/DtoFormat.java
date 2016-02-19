package com.lianshang.generator.format;

import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.TableMeta;
import java.util.Random;

/**
 * Created by walker on 16/2/16.
 */
public class DtoFormat extends EntityFormat {

    public static String getFileContent(String className, String prefixClassPackage, TableMeta meta) throws ServiceException {

        StringBuilder builder = new StringBuilder();
        builder.append("package " + prefixClassPackage + ".api.dto;" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(getWrapImportList(meta));
        builder.append(Constant.RETURN);

        builder.append("public class " + className + " implements Serializable {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        long serialVersionUID = System.currentTimeMillis()*10000 + new Random().nextInt(10000);
        builder.append(Constant.TAB + "private static final long serialVersionUID = " + serialVersionUID + "L;" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(getFieldDefineList(meta));
        builder.append(Constant.RETURN);

        builder.append(getFieldFunctionList(meta));
        builder.append(Constant.RETURN);

        builder.append(getToStringFunctionImpl(meta));
        builder.append(Constant.RETURN);

        builder.append("}" + Constant.RETURN);

        return builder.toString();
    }

    protected static String getWrapImportList(TableMeta meta) throws ServiceException {

        StringBuilder builder = new StringBuilder();
        builder.append("import java.io.Serializable;" + Constant.RETURN);
        builder.append(getImportList(meta));

        return builder.toString();
    }
}
