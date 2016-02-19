package com.lianshang.generator.format;

import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.ColumnMeta;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.StringUtil;
import com.lianshang.generator.util.TableMetaUtil;

/**
 * Created by walker on 16/2/16.
 */
public class ServiceFormat {

    public static String getFileContent(String interfaceName, String dtoClassName, String prefixClassPackage, TableMeta meta) throws ServiceException {

        StringBuilder builder = new StringBuilder();
        builder.append("package " + prefixClassPackage + ".api.service;" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(getImportList(prefixClassPackage, dtoClassName));
        builder.append(Constant.RETURN);

        builder.append("public interface " + interfaceName + " {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB + getAddFunctionDeclaration(dtoClassName, meta) + ";" + Constant.RETURN);

        if (TableMetaUtil.hasLoadFunction(meta)) {
            builder.append(Constant.RETURN);
            builder.append(Constant.TAB + getLoadFunctionDeclaration(dtoClassName, meta) + ";" + Constant.RETURN);
        }

        if (TableMetaUtil.hasUpdateFunction(meta)) {
            builder.append(Constant.RETURN);
            builder.append(Constant.TAB + getUpdateFunctionDeclaration(dtoClassName, meta) + ";" + Constant.RETURN);
        }

        builder.append(Constant.RETURN);
        builder.append(Constant.TAB + getQueryFunctionDeclaration(dtoClassName) + ";" + Constant.RETURN);

        builder.append(Constant.RETURN);
        builder.append(Constant.TAB + getQueryCountFunctionDeclaration() + ";" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append("}" + Constant.RETURN);

        return builder.toString();
    }

    protected static String getLoadFunctionDeclaration(String dtoClassName, TableMeta tableMeta) throws ServiceException{

        StringBuilder builder = new StringBuilder();
        builder.append(dtoClassName + " load(");
        for (int i = 0; i < tableMeta.getPrimaryKeys().size(); ++i) {

            if (i != 0) {
                builder.append(", ");
            }

            ColumnMeta columnMeta = tableMeta.getColumnMeta(tableMeta.getPrimaryKeys().get(i));
            builder.append(FieldFormat.getFieldType(columnMeta.getColumnType()));
            builder.append(" ");
            builder.append(FieldFormat.getFieldName(columnMeta.getColumnName()));
        }
        builder.append(")");

        return builder.toString();
    }

    protected static String getAddFunctionDeclaration(String dtoClassName, TableMeta tableMeta) throws ServiceException{

        String returnType;
        if (tableMeta.isHasAutoIncrementColumn()) {

            ColumnMeta columnMeta = tableMeta.getAutoIncrementColumn();
            returnType = FieldFormat.getFieldType(columnMeta.getColumnType());
        }
        else {
            returnType = "boolean";
        }

        return returnType + " add(" + dtoClassName + " " + StringUtil.toLowerForFirstChar(dtoClassName) + ")";
    }

    protected static String getUpdateFunctionDeclaration(String dtoClassName, TableMeta tableMeta) throws ServiceException{

        String returnType;
        if (tableMeta.isHasAutoIncrementColumn()) {

            ColumnMeta columnMeta = tableMeta.getAutoIncrementColumn();
            returnType = FieldFormat.getFieldType(columnMeta.getColumnType());
        }
        else {
            returnType = "boolean";
        }

        return returnType + " update(" + dtoClassName + " " + StringUtil.toLowerForFirstChar(dtoClassName) + ")";
    }

    protected static String getQueryCountFunctionDeclaration() throws ServiceException{

        return "int queryCount()";
    }

    protected static String getQueryFunctionDeclaration(String dtoClassName) throws ServiceException{

        return "List<" + dtoClassName + "> query(int pageNo, int pageSize)";
    }

    private static String getImportList(String prefixClassPackage, String dtoClassName) {

        StringBuilder builder = new StringBuilder();

        builder.append("import " + prefixClassPackage + ".api.dto." + dtoClassName + ";" + Constant.RETURN);
        builder.append("import java.util.List;" + Constant.RETURN);

        return builder.toString();
    }
}
