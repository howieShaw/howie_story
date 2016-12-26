package com.lianshang.generator.format;

import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.ColumnMeta;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.StringUtil;
import com.lianshang.generator.util.ValueGenerateUtil;

/**
 * Created by vito.zhou on 2016/12/23.
 */
public class DaoTestFormat {

    public static String getFileContent(String daoClassName
            , String entityName
            , String prefixClassPackage
            , TableMeta meta) throws ServiceException {
        StringBuilder sb = new StringBuilder();

        String daoEntityName = StringUtil.toLowerForFirstChar(daoClassName);

        sb.append("package " + prefixClassPackage + ".biz.dao;" + Constant.RETURN);
        sb.append(Constant.RETURN);

        sb.append(getImport(daoClassName, entityName, prefixClassPackage));
        sb.append(Constant.RETURN);
        sb.append("@RunWith(SpringJUnit4ClassRunner.class)" + Constant.RETURN);
        sb.append("@ContextConfiguration(locations = {\"/mock/spring/h2-dao.xml\"})" + Constant.RETURN);
        sb.append("public class " + daoClassName + "Test {" + Constant.RETURN);
        sb.append(Constant.TAB + "@Autowired" + Constant.RETURN);
        sb.append(Constant.TAB + "private " + daoClassName + " " + daoEntityName + ";" + Constant.RETURN);
        sb.append(Constant.RETURN);

        sb.append(getInsertDaoTest(daoClassName, entityName, meta) + Constant.RETURN);

        sb.append("}");

        return sb.toString();
    }

    private static String getImport(String daoClassName, String entityName, String prefixClassPackage) {
        StringBuilder sb = new StringBuilder();
        sb.append("import " + prefixClassPackage + ".biz.entity." + entityName + ";" + Constant.RETURN);
        sb.append("import " + prefixClassPackage + ".biz.dao." + daoClassName + ";" + Constant.RETURN);
        sb.append(Constant.RETURN);

        sb.append("import org.junit.Test;" + Constant.RETURN);
        sb.append("import org.junit.runner.RunWith;" + Constant.RETURN);
        sb.append("import org.springframework.beans.factory.annotation.Autowired;" + Constant.RETURN);
        sb.append("import org.springframework.test.context.ContextConfiguration;" + Constant.RETURN);
        sb.append("import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;" + Constant.RETURN);
        sb.append(Constant.RETURN);

        sb.append("import java.util.ArrayList;" + Constant.RETURN);
        sb.append("import java.util.List;" + Constant.RETURN);
        sb.append("import java.util.Date;" + Constant.RETURN);
        sb.append("import static org.mockito.BDDMockito.then;" + Constant.RETURN);

        return sb.toString();
    }

    private static String getInsertDaoTest(String daoClassName, String entityName, TableMeta meta) throws ServiceException {
        String entityVarName = StringUtil.toLowerForFirstChar(entityName);
        String daoVarClassName=  StringUtil.toLowerForFirstChar(daoClassName);

        StringBuilder sb = new StringBuilder();

        sb.append(Constant.TAB + "@Test" + Constant.RETURN);
        sb.append(Constant.TAB + "public void insertTest() {" + Constant.RETURN);

        sb.append(Constant.RETURN);
        sb.append(Constant.TAB2 + entityName + " " + entityVarName + " = new " + entityName + "();" + Constant.RETURN);
        for (ColumnMeta columnMeta : meta.getColumnMetas()) {

            String setFunctionName = FieldFormat.setFieldFunctionName(columnMeta);

            if (meta.isInPrimaryKeys(columnMeta.getColumnName())) {
                String getFunctionName = FieldFormat.getFieldFunctionName(columnMeta);
                sb.append(Constant.TAB2 + entityVarName + "." + setFunctionName + "(" + entityVarName + "." + getFunctionName + "());" + Constant.RETURN);
            }
            else {
                String value = ValueGenerateUtil.general(columnMeta.getColumnType(), columnMeta.getDataSize());
                sb.append(Constant.TAB2 + entityVarName + "." + setFunctionName + "(" + value + ");" + Constant.RETURN);
            }
        }

        sb.append(Constant.TAB2 + "int num = " + daoVarClassName + ".insert(" + entityVarName + ");" + Constant.RETURN);
        sb.append(Constant.TAB2 + "then(num).equals(1);");

        sb.append(Constant.RETURN);

        sb.append(Constant.TAB + "}");

        return sb.toString();
    }
}
