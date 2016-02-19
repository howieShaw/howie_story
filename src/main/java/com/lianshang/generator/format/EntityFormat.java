package com.lianshang.generator.format;

import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.ColumnMeta;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.StringUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by walker on 16/2/4.
 */
public class EntityFormat {

    public static String getFileContent(String className, String prefixClassPackage, TableMeta meta) throws ServiceException {

        StringBuilder builder = new StringBuilder();
        builder.append("package " + prefixClassPackage + ".biz.entity;" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(getImportList(meta));
        builder.append(Constant.RETURN);

        builder.append("public class " + className + " {" + Constant.RETURN);
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

    public static String getImportList(TableMeta meta) throws ServiceException{

        StringBuilder builder = new StringBuilder();

        Set<String> existed = new HashSet<>();

        for (int i = 0; i < meta.getColumnMetas().size(); ++i) {
            ColumnMeta columnMeta = meta.getColumnMetas().get(i);

            String fieldImport = FieldFormat.getFieldImport(columnMeta);
            if (fieldImport.trim().length() == 0) {
                continue;
            }

            if (existed.contains(fieldImport)) {
                continue;
            }
            existed.add(fieldImport);

            builder.append(fieldImport + Constant.RETURN);
        }

        builder.append("import com.google.common.base.MoreObjects;" + Constant.RETURN);

        return builder.toString();
    }

    protected static String getFieldDefineList(TableMeta meta) throws ServiceException{

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < meta.getColumnMetas().size(); ++i) {
            ColumnMeta columnMeta = meta.getColumnMetas().get(i);

            builder.append(FieldFormat.getFieldDefine(columnMeta));

            if (i != meta.getColumnMetas().size() - 1) {
                builder.append(Constant.RETURN);
            }
        }

        return builder.toString();
    }

    protected static String getFieldFunctionList(TableMeta meta) throws ServiceException{

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < meta.getColumnMetas().size(); ++i) {
            ColumnMeta columnMeta = meta.getColumnMetas().get(i);

            builder.append(FieldFormat.getFieldFunction(columnMeta));
            builder.append(Constant.RETURN);
            builder.append(FieldFormat.setFieldFunction(columnMeta));

            if (i != meta.getColumnMetas().size() - 1) {
                builder.append(Constant.RETURN);
            }
        }

        return builder.toString();
    }

    protected static String getToStringFunctionImpl(TableMeta meta) {

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "@Override" + Constant.RETURN);
        builder.append(Constant.TAB + "public String toString() {" + Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + "return MoreObjects.toStringHelper(this)" + Constant.RETURN);
        for (ColumnMeta columnMeta : meta.getColumnMetas()) {
            String varName = FieldFormat.getFieldName(columnMeta.getColumnName());
            builder.append(Constant.TAB3 + ".add(\"" + varName + "\"," + varName + ")" + Constant.RETURN);
        }
        builder.append(Constant.TAB3 + ".toString();" + Constant.RETURN);
        builder.append(Constant.TAB + "}" + Constant.RETURN);

        return builder.toString();
    }
}
