package com.lianshang.generator.builder;

import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.format.FieldFormat;
import com.lianshang.generator.meta.ColumnMeta;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.FileUtil;
import com.lianshang.generator.util.StringUtil;
import com.lianshang.generator.util.TableMetaUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by walker on 16/2/17.
 */
public class SqlmapBuilder {

    public static void build(ModuleConfig mc, DirectoryConfig dc, List<TableMeta> tableMetaList) throws ServiceException{

        String basicPath = StringUtil.getResourceBasicPath();

        String baseConfig = FileUtil.loadFromFile(basicPath + "/sqlmap/base-config.xml");

        String sqlMapSaveDir = dc.getBizResourcePrefixPath() + "/config/sqlmap/" + mc.getModuleName();
        FileUtil.makeDir(sqlMapSaveDir);

        for (TableMeta meta : tableMetaList) {

            buildTableConfig(mc, meta, baseConfig, sqlMapSaveDir);
        }
    }

    private static void buildTableConfig(ModuleConfig mc, TableMeta meta, String baseConfig, String sqlMapSaveDir) throws ServiceException{

        String path = sqlMapSaveDir + "/" + meta.getPrefixName() + "Mapper.xml";
        String daoInterfaceName = NameUtil.getDaoInterfaceName(meta);

        StringBuilder builder = new StringBuilder();

        builder.append(buildTableResultMap(meta));

        builder.append(Constant.RETURN);
        builder.append(buildTableBaseSql(meta));

        builder.append(Constant.RETURN);
        builder.append(buildTableInsertSql(meta));

        if (TableMetaUtil.hasLoadFunction(meta)) {
            builder.append(Constant.RETURN);
            builder.append(buildTableLoadSql(meta));
        }

        if (TableMetaUtil.hasUpdateFunction(meta)) {
            builder.append(Constant.RETURN);
            builder.append(buildTableUpdateSql(meta));
        }

        builder.append(Constant.RETURN);
        builder.append(buildTableQueryCountSql(meta));

        builder.append(Constant.RETURN);
        builder.append(buildTableQuerySql(meta));

        buildConfig(path, baseConfig, builder.toString(), mc.getPrefixClassPackage(), daoInterfaceName);
    }

    private static String buildTableResultMap(TableMeta meta) {

        String entityClassName = NameUtil.getEntityClassName(meta);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "<resultMap id=\"" + meta.getPrefixName() + "Result\" type=\"" + entityClassName + "\">" + Constant.RETURN);

        for (ColumnMeta columnMeta : meta.getColumnMetas()) {
            String fieldName = FieldFormat.getFieldName(columnMeta.getColumnName());
            if (meta.isInPrimaryKeys(fieldName)) {
                builder.append(Constant.TAB2 + "<id property=\"" + fieldName + "\" column=\"" + columnMeta.getColumnName() + "\" />" + Constant.RETURN);
            }
            else {
                builder.append(Constant.TAB2 + "<result property=\"" + fieldName + "\" column=\"" + columnMeta.getColumnName() + "\" />" + Constant.RETURN);
            }
        }

        builder.append(Constant.TAB + "</resultMap>" + Constant.RETURN);

        return builder.toString();
    }

    private static String buildTableBaseSql(TableMeta meta) {

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "<sql id=\"tbl_name\">" + Constant.RETURN);
        builder.append(Constant.TAB2 + meta.getTableName() + Constant.RETURN);
        builder.append(Constant.TAB + "</sql>" + Constant.RETURN);

        builder.append(Constant.RETURN);

        builder.append(Constant.TAB + "<sql id=\"select_sql\">" + Constant.RETURN);
        builder.append(Constant.TAB2 + "select ");
        for (int i = 0; i < meta.getColumnMetas().size(); ++i) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(meta.getColumnMetas().get(i).getColumnName());
        }
        builder.append(" from <include refid=\"tbl_name\"/>" + Constant.RETURN);
        builder.append(Constant.TAB + "</sql>" + Constant.RETURN);

        return builder.toString();
    }

    private static String buildTableInsertSql(TableMeta meta) throws ServiceException{

        String entityClassName = NameUtil.getEntityClassName(meta);

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "<insert id=\"insert\" parameterType=\"" + entityClassName + "\"");
        if (meta.isHasAutoIncrementColumn()) {
            ColumnMeta columnMeta = meta.getAutoIncrementColumn();
            builder.append(" useGeneratedKeys=\"true\" keyProperty=\"entity." + FieldFormat.getFieldName(columnMeta.getColumnName()) + "\"");
        }
        builder.append(">" + Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + "insert into <include refid=\"tbl_name\" />" + Constant.RETURN);
        builder.append(Constant.TAB2 + "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >" + Constant.RETURN);

        for (ColumnMeta columnMeta : meta.getColumnMetas()) {
            if (columnMeta.isAutoIncrement()) {
                continue;
            }
            String formatColumn = FieldFormat.getFieldName(columnMeta.getColumnName());

            builder.append(Constant.TAB3 + "<if test=\"entity.");
            builder.append(formatColumn + " != null and entity." + formatColumn + " != ''\" >" + Constant.RETURN);
            builder.append(Constant.TAB4 + columnMeta.getColumnName() + "," + Constant.RETURN);
            builder.append(Constant.TAB3 + "</if>" + Constant.RETURN);
        }

        builder.append(Constant.TAB2 + "</trim>" + Constant.RETURN);
        builder.append(Constant.TAB2 + "<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >" + Constant.RETURN);

        for (ColumnMeta columnMeta: meta.getColumnMetas()) {
            if (columnMeta.isAutoIncrement()) {
                continue;
            }
            String formatColumn = FieldFormat.getFieldName(columnMeta.getColumnName());

            builder.append(Constant.TAB3 + "<if test=\"entity.");
            builder.append(formatColumn + " != null and entity." + formatColumn + " != ''\" >" + Constant.RETURN);
            builder.append(Constant.TAB4 + "#{entity." + formatColumn + "}," + Constant.RETURN);
            builder.append(Constant.TAB3 + "</if>" + Constant.RETURN);
        }
        builder.append(Constant.TAB2 + "</trim>" + Constant.RETURN);
        builder.append(Constant.TAB + "</insert>" + Constant.RETURN);

        return builder.toString();
    }

    private static String buildTableLoadSql(TableMeta meta) {

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "<select id=\"load\" parameterType=\"map\" resultMap=\"" + meta.getPrefixName() + "Result\">" + Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + "<include refid=\"select_sql\"/>" + Constant.RETURN);
        builder.append(Constant.TAB2 + "where ");
        for (int i = 0; i < meta.getPrimaryKeys().size(); ++i) {
            if (i != 0) {
                builder.append(" and ");
            }

            ColumnMeta columnMeta = meta.getColumnMeta(meta.getPrimaryKeys().get(i));
            String fieldName = FieldFormat.getFieldName(columnMeta.getColumnName());
            builder.append(columnMeta.getColumnName() + " = #{" + fieldName + "}");
        }
        builder.append(Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB + "</select>" + Constant.RETURN);

        return builder.toString();
    }

    private static String buildTableUpdateSql(TableMeta meta) {

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "<update id=\"update\" parameterType=\"map\">" + Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + "update <include refid=\"tbl_name\"/>" + Constant.RETURN);
        builder.append(Constant.TAB2 + "<set >" + Constant.RETURN);

        for (ColumnMeta columnMeta : meta.getColumnMetas()) {
            if (meta.isInPrimaryKeys(columnMeta.getColumnName())) {
                continue;
            }

            String fieldName = FieldFormat.getFieldName(columnMeta.getColumnName());
            builder.append(Constant.TAB3 + "<if test=\"entity." + fieldName + " != null and entity." + fieldName + " != ''\" >" + Constant.RETURN);
            builder.append(Constant.TAB4 + columnMeta.getColumnName() + " = #{entity." + fieldName + "}," + Constant.RETURN);
            builder.append(Constant.TAB3 + "</if>" + Constant.RETURN);
        }
        builder.append(Constant.TAB2 + "</set>" + Constant.RETURN);
        builder.append(Constant.TAB2 + "where ");
        for (int i = 0; i < meta.getPrimaryKeys().size(); ++i) {
            if (i != 0) {
                builder.append(" and ");
            }

            ColumnMeta columnMeta = meta.getColumnMeta(meta.getPrimaryKeys().get(i));
            String fieldName = FieldFormat.getFieldName(columnMeta.getColumnName());
            builder.append(columnMeta.getColumnName() + " = #{entity." + fieldName + "}");
        }
        builder.append(Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB + "</update>" + Constant.RETURN);

        return builder.toString();
    }

    private static String buildTableQueryCountSql(TableMeta meta) {

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "<select id=\"queryCount\" parameterType=\"map\" resultType=\"int\">" + Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + "select count(1) from <include refid=\"tbl_name\"/>" + Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB + "</select>" + Constant.RETURN);

        return builder.toString();
    }

    private static String buildTableQuerySql(TableMeta meta) {

        StringBuilder builder = new StringBuilder();
        builder.append(Constant.TAB + "<select id=\"query\" parameterType=\"map\" resultMap=\"" + meta.getPrefixName() + "Result\">" + Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB2 + "<include refid=\"select_sql\"/>" + Constant.RETURN);
        builder.append(Constant.TAB2 + "limit #{offset}, #{pageSize}" + Constant.RETURN);
        builder.append(Constant.RETURN);
        builder.append(Constant.TAB + "</select>" + Constant.RETURN);

        return builder.toString();
    }

    private static void buildConfig(String path
            , String baseConfig
            , String content
            , String packageName
            , String daoInterfaceName) {

        Map<String, String> replaceParams = new HashMap<>();
        replaceParams.put("[{]CONTENT[}]", content);
        replaceParams.put("[{]PACKAGE[}]", packageName);
        replaceParams.put("[{]DAO-INTERFACE-NAME[}]", daoInterfaceName);

        boolean result = FileUtil.writeFile(path, StringUtil.replace(baseConfig, replaceParams));
        assert result;
    }

}
