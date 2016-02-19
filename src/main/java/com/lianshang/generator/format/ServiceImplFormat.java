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
public class ServiceImplFormat extends ServiceFormat {

    public static String getFileContent(String className
            , String implementName
            , String dtoClassName
            , String entityClassName
            , String daoImplementName
            , String prefixClassPackage
            , TableMeta meta) throws ServiceException {

        StringBuilder builder = new StringBuilder();
        builder.append("package " + prefixClassPackage + ".biz.service.impl;" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(getImportList(prefixClassPackage
                , implementName
                , dtoClassName
                , entityClassName
                , daoImplementName));
        builder.append(Constant.RETURN);

        builder.append("public class " + className + " implements " + implementName + " {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(getAutowiredList(daoImplementName));
        builder.append(Constant.RETURN);

        builder.append(getAddFunctionImpl(dtoClassName, entityClassName, daoImplementName, meta) + Constant.RETURN);

        if (TableMetaUtil.hasLoadFunction(meta)) {
            builder.append(Constant.RETURN);
            builder.append(getLoadFunctionImpl(dtoClassName, entityClassName, daoImplementName, meta) + ";" + Constant.RETURN);
        }

        if (TableMetaUtil.hasUpdateFunction(meta)) {
            builder.append(Constant.RETURN);
            builder.append(getUpdateFunctionImpl(dtoClassName, entityClassName, daoImplementName, meta) + Constant.RETURN);
        }

        builder.append(Constant.RETURN);
        builder.append(getQueryFunctionImpl(dtoClassName, entityClassName, daoImplementName, meta) + Constant.RETURN);

        builder.append(getQueryCountFunctionImpl(daoImplementName));
        builder.append(Constant.RETURN);

        builder.append(getDto2EntityFunctionImpl(dtoClassName, entityClassName, meta));
        builder.append(Constant.RETURN);

        builder.append(getEntity2DtoFunctionImpl(entityClassName, dtoClassName, meta));
        builder.append(Constant.RETURN);

        builder.append("}" + Constant.RETURN);

        return builder.toString();
    }

    private static String getAddFunctionImpl(String dtoClassName
            , String entityClassName
            , String daoImplementName
            , TableMeta meta) throws ServiceException{

        String dtoVar = StringUtil.toLowerForFirstChar(dtoClassName);
        String entityVar = StringUtil.toLowerForFirstChar(entityClassName);
        String daoVar = StringUtil.toLowerForFirstChar(daoImplementName);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "@Override" + Constant.RETURN);
        builder.append(Constant.TAB + "public " + getAddFunctionDeclaration(dtoClassName, meta) + " {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB2 + entityClassName + " " + entityVar + " = dto2entity(" + dtoVar + ");" + Constant.RETURN);
        builder.append(Constant.TAB2 + daoVar  + ".insert(" + entityVar + ");" + Constant.RETURN);
        if (!meta.isHasAutoIncrementColumn()) {
            builder.append(Constant.TAB2 + "return true;" + Constant.RETURN);
        }
        else {
            ColumnMeta columnMeta = meta.getAutoIncrementColumn();
            builder.append(Constant.TAB2 + "return " + entityVar + "." + FieldFormat.getFieldFunctionName(columnMeta) + "();" + Constant.RETURN);
        }

        builder.append(Constant.TAB + "}" + Constant.RETURN);

        return builder.toString();
    }

    private static String getUpdateFunctionImpl(String dtoClassName
            , String entityClassName
            , String daoImplementName
            , TableMeta meta) throws ServiceException{

        String dtoVar = StringUtil.toLowerForFirstChar(dtoClassName);
        String entityVar = StringUtil.toLowerForFirstChar(entityClassName);
        String daoVar = StringUtil.toLowerForFirstChar(daoImplementName);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "@Override" + Constant.RETURN);
        builder.append(Constant.TAB + "public " + getUpdateFunctionDeclaration(dtoClassName, meta) + " {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB2 + entityClassName + " " + entityVar + " = dto2entity(" + dtoVar + ");" + Constant.RETURN);
        builder.append(Constant.TAB2 + daoVar  + ".update(" + entityVar + ");" + Constant.RETURN);
        if (!meta.isHasAutoIncrementColumn()) {
            builder.append(Constant.TAB2 + "return true;" + Constant.RETURN);
        }
        else {
            ColumnMeta columnMeta = meta.getAutoIncrementColumn();
            builder.append(Constant.TAB2 + "return " + entityVar + "." + FieldFormat.getFieldFunctionName(columnMeta) + "();" + Constant.RETURN);
        }

        builder.append(Constant.TAB + "}" + Constant.RETURN);

        return builder.toString();
    }

    private static String getLoadFunctionImpl(String dtoClassName
            , String entityClassName
            , String daoImplementName
            , TableMeta meta) throws ServiceException{

        String entityVar = StringUtil.toLowerForFirstChar(entityClassName);
        String daoVar = StringUtil.toLowerForFirstChar(daoImplementName);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "@Override" + Constant.RETURN);
        builder.append(Constant.TAB + "public " + getLoadFunctionDeclaration(dtoClassName, meta) + " {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB2 + entityClassName + " " + entityVar + " = " + daoVar  + ".load(");
        for (int i = 0; i < meta.getPrimaryKeys().size(); ++i) {

            if (i != 0) {
                builder.append(", ");
            }

            ColumnMeta columnMeta = meta.getColumnMeta(meta.getPrimaryKeys().get(i));
            builder.append(FieldFormat.getFieldName(columnMeta.getColumnName()));
        }
        builder.append(");" + Constant.RETURN);

        builder.append(Constant.TAB2 + "return entity2dto(" + entityVar + ");" + Constant.RETURN);


        builder.append(Constant.TAB + "}" + Constant.RETURN);

        return builder.toString();
    }

    private static String getQueryFunctionImpl(String dtoClassName
            , String entityClassName
            , String daoImplementName
            , TableMeta meta) throws ServiceException{

        String entityVar = StringUtil.toLowerForFirstChar(entityClassName);
        String daoVar = StringUtil.toLowerForFirstChar(daoImplementName);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "@Override" + Constant.RETURN);
        builder.append(Constant.TAB + "public " + getQueryFunctionDeclaration(dtoClassName) + " {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB2 + "List<" + entityClassName + "> entityList = " + daoVar  + ".query((pageNo-1)*pageSize, pageSize);" + Constant.RETURN);
        builder.append(Constant.TAB2 + "if (entityList == null) {" + Constant.RETURN);
        builder.append(Constant.TAB3 + "return null;" + Constant.RETURN);
        builder.append(Constant.TAB2 +"}" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB2 + "List<" + dtoClassName + "> list = new ArrayList<>();" + Constant.RETURN);
        builder.append(Constant.TAB2 + "for (" + entityClassName + " " + entityVar + " : entityList) {" + Constant.RETURN);
        builder.append(Constant.TAB3 + "list.add(entity2dto(" + entityVar + "));" + Constant.RETURN);
        builder.append(Constant.TAB2 + "}" + Constant.RETURN);

        builder.append(Constant.TAB2 + "return list;" + Constant.RETURN);


        builder.append(Constant.TAB + "}" + Constant.RETURN);

        return builder.toString();
    }

    private static String getQueryCountFunctionImpl(String daoImplementName) throws ServiceException{

        String daoVar = StringUtil.toLowerForFirstChar(daoImplementName);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "@Override" + Constant.RETURN);
        builder.append(Constant.TAB + "public " + getQueryCountFunctionDeclaration() + " {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB2 + "return " + daoVar + ".queryCount();" + Constant.RETURN);

        builder.append(Constant.TAB + "}" + Constant.RETURN);

        return builder.toString();

    }

    private static String getDto2EntityFunctionImpl(String dtoClassName
            , String entityClassName
            , TableMeta meta) throws ServiceException {

        String dtoVar = StringUtil.toLowerForFirstChar(dtoClassName);
        String entityVar = StringUtil.toLowerForFirstChar(entityClassName);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "private " + entityClassName + " dto2entity(" + dtoClassName + " " + dtoVar + ") {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB2 + "if (" + dtoVar + " == null) {" + Constant.RETURN);
        builder.append(Constant.TAB3 + "return null;" + Constant.RETURN);
        builder.append(Constant.TAB2 +"}" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB2 + entityClassName + " " + entityVar + " = new " + entityClassName + "();" + Constant.RETURN);
//        builder.append(Constant.TAB2 + "BeanUtils.copyProperties(" + dtoVar + ", " + entityVar + ");" + Constant.RETURN);
        for (ColumnMeta columnMeta : meta.getColumnMetas()) {
            String setFunctionName = FieldFormat.setFieldFunctionName(columnMeta);
            String getFunctionName = FieldFormat.getFieldFunctionName(columnMeta);
            builder.append(Constant.TAB2 + entityVar + "." + setFunctionName + "(" + dtoVar + "." + getFunctionName + "());" + Constant.RETURN);
        }
        builder.append(Constant.TAB2 + "return " + entityVar + ";" + Constant.RETURN);

        builder.append(Constant.TAB + "}" + Constant.RETURN);

        return builder.toString();

    }

    private static String getEntity2DtoFunctionImpl(String entityClassName
            , String dtoClassName
            , TableMeta meta) throws ServiceException{

        String dtoVar = StringUtil.toLowerForFirstChar(dtoClassName);
        String entityVar = StringUtil.toLowerForFirstChar(entityClassName);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "private " + dtoClassName + " entity2dto(" + entityClassName + " " + entityVar + ") {" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB2 + "if (" + entityVar + " == null) {" + Constant.RETURN);
        builder.append(Constant.TAB3 + "return null;" + Constant.RETURN);
        builder.append(Constant.TAB2 +"}" + Constant.RETURN);
        builder.append(Constant.RETURN);

        builder.append(Constant.TAB2 + dtoClassName + " " + dtoVar + " = new " + dtoClassName + "();" + Constant.RETURN);
//        builder.append(Constant.TAB2 + "BeanUtils.copyProperties(" + entityVar + ", " + dtoVar + ");" + Constant.RETURN);
        for (ColumnMeta columnMeta : meta.getColumnMetas()) {
            String setFunctionName = FieldFormat.setFieldFunctionName(columnMeta);
            String getFunctionName = FieldFormat.getFieldFunctionName(columnMeta);
            builder.append(Constant.TAB2 + dtoVar + "." + setFunctionName + "(" + entityVar + "." + getFunctionName + "());" + Constant.RETURN);
        }
        builder.append(Constant.TAB2 + "return " + dtoVar + ";" + Constant.RETURN);

        builder.append(Constant.TAB + "}" + Constant.RETURN);

        return builder.toString();

    }

    private static String getImportList(String prefixClassPackage
            , String implementName
            , String dtoClassName
            , String entityClassName
            , String daoImplementName) {

        StringBuilder builder = new StringBuilder();

        builder.append("import " + prefixClassPackage + ".api.dto." + dtoClassName + ";" + Constant.RETURN);
        builder.append("import " + prefixClassPackage + ".api.service." + implementName + ";" + Constant.RETURN);
        builder.append("import " + prefixClassPackage + ".biz.entity." + entityClassName + ";" + Constant.RETURN);
        builder.append("import " + prefixClassPackage + ".biz.dao." + daoImplementName + ";" + Constant.RETURN);
//        builder.append("import org.springframework.beans.BeanUtils;" + Constant.RETURN);
        builder.append("import java.util.List;" + Constant.RETURN);
        builder.append("import java.util.ArrayList;" + Constant.RETURN);
        builder.append("import org.springframework.beans.factory.annotation.Autowired;;" + Constant.RETURN);

        return builder.toString();
    }

    private static String getAutowiredList(String daoImplementName) {

        String varName = StringUtil.toLowerForFirstChar(daoImplementName);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "@Autowired" + Constant.RETURN);
        builder.append(Constant.TAB + "private " + daoImplementName + " " + varName + ";" + Constant.RETURN);

        return builder.toString();
    }

}
