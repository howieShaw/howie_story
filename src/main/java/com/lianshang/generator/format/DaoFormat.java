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
public class DaoFormat {

    public static String getFileContent(String interfaceName, String entityClassName, String prefixClassPackage, TableMeta meta) throws ServiceException {

        StringBuilder builder = new StringBuilder();
        builder.append("package " + prefixClassPackage + ".biz.dao;" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(getImportList(prefixClassPackage, entityClassName));
        builder.append(Constant.RETURN);

        builder.append("public interface " + interfaceName + " {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(getInsertFunctionDeclaration(entityClassName, meta) + ";" + Constant.RETURN);

        if (TableMetaUtil.hasLoadFunction(meta)) {
            builder.append(Constant.RETURN);
            builder.append(getLoadFunctionDeclaration(entityClassName, meta) + ";" + Constant.RETURN);
        }

        if (TableMetaUtil.hasUpdateFunction(meta)) {
            builder.append(Constant.RETURN);
            builder.append(getUpdateFunctionDeclaration(entityClassName, meta) + ";" + Constant.RETURN);
        }

        builder.append(Constant.RETURN);
        builder.append(getQueryFunctionDeclaration(entityClassName, meta) + ";" + Constant.RETURN);

        builder.append(Constant.RETURN);
        builder.append(getQueryCountFunctionDeclaration(entityClassName, meta) + ";" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append("}" + Constant.RETURN);

        return builder.toString();
    }

    protected static String getLoadFunctionDeclaration(String entityClassName, TableMeta tableMeta) throws ServiceException{

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + entityClassName + " load(" + Constant.RETURN);
        for (int i = 0; i < tableMeta.getPrimaryKeys().size(); ++i) {

            if (i != 0) {
                builder.append(", ");
            }

            ColumnMeta columnMeta = tableMeta.getColumnMeta(tableMeta.getPrimaryKeys().get(i));

            String filedName = FieldFormat.getFieldName(columnMeta.getColumnName());

            builder.append(Constant.TAB2 + "@Param(\"" + filedName + "\") ");
            builder.append(FieldFormat.getFieldType(columnMeta.getColumnType()));
            builder.append(" ");
            builder.append(filedName);

            builder.append(Constant.TAB + Constant.RETURN);
        }
        builder.append(Constant.TAB + ")");

        return builder.toString();
    }

    protected static String getInsertFunctionDeclaration(String entityClassName, TableMeta tableMeta) throws ServiceException{

        String varName = StringUtil.toLowerForFirstChar(entityClassName);

        return Constant.TAB + "int insert(" + Constant.RETURN
                + Constant.TAB2 + "@Param(\"entity\") " + entityClassName + " " + varName + Constant.RETURN
                + Constant.TAB + ")";
    }

    protected static String getUpdateFunctionDeclaration(String entityClassName, TableMeta tableMeta) throws ServiceException{

        String varName = StringUtil.toLowerForFirstChar(entityClassName);

        return Constant.TAB + "int update(" + Constant.RETURN
                + Constant.TAB2 + "@Param(\"entity\") " + entityClassName + " " + varName + Constant.RETURN
                + Constant.TAB + ")";
    }

    protected static String getQueryCountFunctionDeclaration(String entityClassName, TableMeta tableMeta) throws ServiceException{

        return Constant.TAB + "int queryCount(" + Constant.RETURN
                + Constant.TAB + ")";
    }

    protected static String getQueryFunctionDeclaration(String entityClassName, TableMeta tableMeta) throws ServiceException{

        return Constant.TAB + "List<" + entityClassName + "> query(" + Constant.RETURN
                + Constant.TAB2 + "@Param(\"offset\") int offset," + Constant.RETURN
                + Constant.TAB2 + "@Param(\"pageSize\") int pageSize" + Constant.RETURN
                + Constant.TAB + ")";
    }

    private static String getImportList(String prefixClassPackage, String entityClassName) {

        StringBuilder builder = new StringBuilder();
        builder.append("import " + prefixClassPackage + ".biz.entity." + entityClassName + ";" + Constant.RETURN);
        builder.append("import org.apache.ibatis.annotations.Param;" + Constant.RETURN);
        builder.append("import java.util.List;" + Constant.RETURN);

        return builder.toString();
    }
}
