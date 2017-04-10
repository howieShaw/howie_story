package com.lianshang.generator.format;

import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.ColumnMeta;
import com.lianshang.generator.meta.ColumnType;
import com.lianshang.generator.util.StringUtil;

/**
 * Created by walker on 16/2/4.
 */
public class FieldFormat {

    public static String getFieldDefine(ColumnMeta meta) throws ServiceException{

        StringBuilder builder = new StringBuilder();
        if (StringUtil.isNotEmpty(meta.getComment())) {
            builder.append(Constant.TAB + "/**" + meta.getComment() + "**/" + Constant.RETURN);
        }
        builder.append(Constant.TAB + "private " + getFieldType(meta.getColumnType())
                + " " + getFieldName(meta.getColumnName()) + ";" + Constant.RETURN);

        return builder.toString();
    }

    public static String getFieldFunction(ColumnMeta meta) throws ServiceException{

        StringBuffer buffer = new StringBuffer();

        String fieldName = getFieldName(meta.getColumnName());
        String fieldType = getFieldType(meta.getColumnType());

        String functionName = getFieldFunctionName(meta);

        buffer.append(Constant.TAB + "public " + fieldType + " " + functionName + "() {" + Constant.RETURN);
        buffer.append(Constant.TAB2 + "return " + fieldName + ";" + Constant.RETURN);
        buffer.append(Constant.TAB + "}" + Constant.RETURN);

        return buffer.toString();
    }

    public static String getFieldFunctionName(ColumnMeta meta) throws ServiceException{

        String fieldName = getFieldName(meta.getColumnName());

        String functionName;
        if (meta.getColumnType() == ColumnType.BOOl) {
            if (fieldName.startsWith("is")) {
                functionName = fieldName;
            }
            else {
                functionName = "is" + StringUtil.toUpperForFirstChar(fieldName);
            }
        }
        else {
            functionName = "get" + StringUtil.toUpperForFirstChar(fieldName);
        }

        return functionName;
    }

    public static String setFieldFunction(ColumnMeta meta) throws ServiceException{

        StringBuffer buffer = new StringBuffer();

        String fieldName = getFieldName(meta.getColumnName());
        String fieldType = getFieldType(meta.getColumnType());

        String functionName = setFieldFunctionName(meta);

        buffer.append(Constant.TAB + "public void " + functionName + "(" + fieldType + " " + fieldName + ") {" + Constant.RETURN);
        buffer.append(Constant.TAB2 + "this." + fieldName + " = " + fieldName + ";" + Constant.RETURN);
        buffer.append(Constant.TAB + "}" + Constant.RETURN);

        return buffer.toString();
    }

    public static String setFieldFunctionName(ColumnMeta meta) {

        String fieldName = getFieldName(meta.getColumnName());
        return "set" + StringUtil.toUpperForFirstChar(fieldName);
    }

    public static String getFieldImport(ColumnMeta meta) {

        switch (meta.getColumnType()) {
            case DECIMAL:
                return "import java.math.BigDecimal;";
            case DATE:
                return "import java.util.Date;";
            default:
                return "";
        }
    }

    public static String getFieldName(String name) {

        StringBuilder builder = new StringBuilder();

        boolean nextNeedSuperCase = false;
        for (int i = 0; i < name.length(); ++i) {

            char ch = name.charAt(i);
            if (i == 0) {
                builder.append(StringUtil.getLowerChar(ch));
            }
            else {
                if ('_' == name.charAt(i)) {
                    nextNeedSuperCase = true;
                }
                else {
                    if (nextNeedSuperCase || StringUtil.isUpperChar(ch)) {
                        builder.append(StringUtil.getUpperChar(ch));
                    }
                    else {
                        builder.append(StringUtil.getLowerChar(ch));
                    }

                    nextNeedSuperCase = false;
                }
            }
        }

        return builder.toString();
    }

    public static String getFieldType(ColumnType columnType) throws ServiceException{

        switch (columnType) {
            case BOOl:
                return "Integer";
            case BYTE:
                return "Integer";
            case INT:
                return "Integer";
            case BIGINT:
                return "Long";
            case STRING:
                return "String";
            case DOUBLE:
                return "Double";
            case DECIMAL:
                return "BigDecimal";
            case DATE:
                return "Date";
            default:
                throw new ServiceException(-1, columnType + "not supported columnType");
        }
    }

}
