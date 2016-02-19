package com.lianshang.generator.format;

import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.ColumnMeta;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.StringUtil;
import com.lianshang.generator.util.TableMetaUtil;
import com.lianshang.generator.util.ValueGenerateUtil;

/**
 * Created by walker on 16/2/18.
 */
public class ServiceTestFormat {

    public static String getFileContent(String className
            , String implementName
            , String dtoClassName
            , String prefixClassPackage
            , TableMeta meta) throws ServiceException {

        StringBuilder builder = new StringBuilder();
        builder.append("package " + prefixClassPackage + ".biz.service;" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(getImportList(prefixClassPackage
                , implementName
                , dtoClassName
                , meta));
        builder.append(Constant.RETURN);

        builder.append("public class " + className + " extends BaseTest {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(getAutowiredList(implementName));
        builder.append(Constant.RETURN);

        builder.append(getTestFunctionImpl(implementName, dtoClassName, meta));
        builder.append(Constant.RETURN);

        builder.append(getTestMockAddDataFunctionImpl(dtoClassName, meta));
        builder.append(Constant.RETURN);

        builder.append(getTestMockUpdateDataFunctionImpl(dtoClassName, meta));
        builder.append(Constant.RETURN);

        builder.append("}" + Constant.RETURN);

        return builder.toString();
    }

    private static String getTestFunctionImpl(String implementName
            , String dtoClassName
            , TableMeta meta) throws ServiceException{

        String dtoVarName = StringUtil.toLowerForFirstChar(dtoClassName);
        String implementVarName = StringUtil.toLowerForFirstChar(implementName);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "@Test" + Constant.RETURN);
        builder.append(Constant.TAB + "public void test() {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB2 + dtoClassName + " " + dtoVarName + " =  mockAddData();" + Constant.RETURN);
        builder.append(Constant.RETURN);

        if (meta.isHasAutoIncrementColumn()) {
            ColumnMeta columnMeta = meta.getAutoIncrementColumn();
            String fieldName = FieldFormat.getFieldName(columnMeta.getColumnName());
            String addFieldVarName = StringUtil.toLowerForFirstChar(fieldName) + "Add";
            String fieldType = FieldFormat.getFieldType(columnMeta.getColumnType());
            builder.append(Constant.TAB2 + fieldType + " " + addFieldVarName + " = " + implementVarName +  ".add(" + dtoVarName + ");" + Constant.RETURN);
            builder.append(Constant.TAB2 + "assert " + addFieldVarName + " > 0;" + Constant.RETURN);
            builder.append(Constant.TAB2 + dtoVarName + "." + FieldFormat.setFieldFunctionName(columnMeta) + "(" + addFieldVarName + ");" + Constant.RETURN);
        }
        else {
            builder.append(Constant.TAB2 + "boolean resultAdd = " + implementVarName + ".add(" + dtoVarName + ");" + Constant.RETURN);
            builder.append(Constant.TAB2 + "assert resultAdd;" + Constant.RETURN);
        }

        if (TableMetaUtil.hasLoadFunction(meta)) {
            builder.append(Constant.RETURN);

            builder.append(Constant.TAB2 + dtoVarName + " = " + implementVarName +  ".load(");
            for (int i = 0; i < meta.getPrimaryKeys().size(); ++i) {

                if (i != 0) {
                    builder.append(", ");
                }

                ColumnMeta columnMeta = meta.getColumnMeta(meta.getPrimaryKeys().get(i));
                String getFunctionName = FieldFormat.getFieldFunctionName(columnMeta);
                builder.append(dtoVarName + "." + getFunctionName + "()");
            }
            builder.append(");" + Constant.RETURN);
            builder.append(Constant.TAB2 + "System.out.println(" + dtoVarName + ");" + Constant.RETURN);
        }
        if (TableMetaUtil.hasUpdateFunction(meta)) {
            builder.append(Constant.RETURN);

            builder.append(Constant.TAB2 + dtoVarName + " = mockUpdateData(" + dtoVarName + ");" + Constant.RETURN);

            if (meta.isHasAutoIncrementColumn()) {
                ColumnMeta columnMeta = meta.getAutoIncrementColumn();
                String fieldName = FieldFormat.getFieldName(columnMeta.getColumnName());
                String updateFieldVarName = StringUtil.toLowerForFirstChar(fieldName) + "Update";
                String fieldType = FieldFormat.getFieldType(columnMeta.getColumnType());
                builder.append(Constant.TAB2 + fieldType + " " + updateFieldVarName + " = " + implementVarName + ".update(" + dtoVarName + ");" + Constant.RETURN);
                builder.append(Constant.TAB2 + "assert " + updateFieldVarName + " > 0;" + Constant.RETURN);
            } else {
                builder.append(Constant.TAB2 + "boolean resultUpdate = " + implementVarName + ".update(" + dtoVarName + ");" + Constant.RETURN);
                builder.append(Constant.TAB2 + "assert resultUpdate;" + Constant.RETURN);
            }
        }

        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + "int queryCount = " + implementVarName + ".queryCount();" + Constant.RETURN);
        builder.append(Constant.TAB2 + "assert queryCount > 0;" + Constant.RETURN);

        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + "List<" + dtoClassName + "> queryList  = " + implementVarName + ".query(1, 20);" + Constant.RETURN);
        builder.append(Constant.TAB2 + "assert queryList.size() > 0;" + Constant.RETURN);
        builder.append(Constant.TAB2 + "System.out.println(queryList);" + Constant.RETURN);

        builder.append(Constant.RETURN);

        builder.append(Constant.TAB + "}" + Constant.RETURN);

        return builder.toString();
    }

    private static String getTestMockAddDataFunctionImpl(String dtoClassName, TableMeta meta) throws ServiceException {

        StringBuilder builder = new StringBuilder();

        String dtoVarName = StringUtil.toLowerForFirstChar(dtoClassName);

        builder.append(Constant.TAB + "private " + dtoClassName + " mockAddData() {" + Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + dtoClassName + " " + dtoVarName + " = new " + dtoClassName + "();" + Constant.RETURN);
        for (ColumnMeta columnMeta : meta.getColumnMetas()) {
            String value = ValueGenerateUtil.general(columnMeta.getColumnType(), columnMeta.getDataSize());
            String setFunctionName = FieldFormat.setFieldFunctionName(columnMeta);
            builder.append(Constant.TAB2 + dtoVarName + "." + setFunctionName + "(" + value + ");" + Constant.RETURN);
        }
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + "return " + dtoVarName + ";" + Constant.RETURN);
        builder.append(Constant.TAB + "}" + Constant.RETURN);

        return builder.toString();
    }

    private static String getTestMockUpdateDataFunctionImpl(String dtoClassName, TableMeta meta) throws ServiceException {

        StringBuilder builder = new StringBuilder();

        String dtoVarName = StringUtil.toLowerForFirstChar(dtoClassName);
        String dtoVarNameUpdate = StringUtil.toLowerForFirstChar(dtoClassName) + "Update";

        builder.append(Constant.TAB + "private " + dtoClassName + " mockUpdateData(" + dtoClassName + " " + dtoVarName + ") {" + Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + dtoClassName + " " + dtoVarNameUpdate + " = new " + dtoClassName + "();" + Constant.RETURN);
        for (ColumnMeta columnMeta : meta.getColumnMetas()) {

            String setFunctionName = FieldFormat.setFieldFunctionName(columnMeta);

            if (meta.isInPrimaryKeys(columnMeta.getColumnName())) {
                String getFunctionName = FieldFormat.getFieldFunctionName(columnMeta);
                builder.append(Constant.TAB2 + dtoVarNameUpdate + "." + setFunctionName + "(" + dtoVarName + "." + getFunctionName + "());" + Constant.RETURN);
            }
            else {
                String value = ValueGenerateUtil.general(columnMeta.getColumnType(), columnMeta.getDataSize());
                builder.append(Constant.TAB2 + dtoVarNameUpdate + "." + setFunctionName + "(" + value + ");" + Constant.RETURN);
            }
        }
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + "return " + dtoVarNameUpdate + ";" + Constant.RETURN);
        builder.append(Constant.TAB + "}" + Constant.RETURN);

        return builder.toString();
    }

    private static String getImportList(String prefixClassPackage
            , String implementName
            , String dtoClassName
            , TableMeta meta) throws ServiceException{

        StringBuilder builder = new StringBuilder();

        builder.append(EntityFormat.getImportList(meta));
        builder.append("import " + prefixClassPackage + ".api.dto." + dtoClassName + ";" + Constant.RETURN);
        builder.append("import " + prefixClassPackage + ".api.service." + implementName + ";" + Constant.RETURN);
        builder.append("import " + prefixClassPackage + ".biz.BaseTest;" + Constant.RETURN);
        builder.append("import org.junit.Test;" + Constant.RETURN);
        builder.append("import java.util.List;" + Constant.RETURN);
        builder.append("import org.springframework.beans.factory.annotation.Autowired;" + Constant.RETURN);

        return builder.toString();
    }

    private static String getAutowiredList(String implementName) {

        String varName = StringUtil.toLowerForFirstChar(implementName);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "@Autowired" + Constant.RETURN);
        builder.append(Constant.TAB + "private " + implementName + " " + varName + ";" + Constant.RETURN);

        return builder.toString();
    }
}
