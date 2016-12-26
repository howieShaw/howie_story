package com.lianshang.generator.meta;

import com.google.common.base.MoreObjects;
import com.lianshang.generator.util.StringUtil;

import java.util.List;

/**
 * Created by walker on 16/2/3.
 */
public class TableMeta {

    private String tableName;

    private List<ColumnMeta> columnMetas;

    private List<String> primaryKeys;

    private String prefixName;

    private String createTableSql;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnMeta> getColumnMetas() {
        return columnMetas;
    }

    public void setColumnMetas(List<ColumnMeta> columnMetas) {
        this.columnMetas = columnMetas;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getCreateTableSql() {
        return createTableSql;
    }

    public void setCreateTableSql(String createTableSql) {
        for (ColumnMeta meta : columnMetas) {
            String comment = meta.getComment();
            if (!StringUtil.isEmpty(comment)) {
                String replaceComment = "COMMENT '" + comment + "'";
                createTableSql = createTableSql.replace(replaceComment, "");
                createTableSql = createTableSql.replace("ON UPDATE CURRENT_TIMESTAMP", "");
            }
        }
        this.createTableSql = createTableSql;
    }

    public ColumnMeta getColumnMeta(String name) {

        for (ColumnMeta meta : columnMetas) {
            if (meta.getColumnName().equals(name)) {
                return meta;
            }
        }

        return null;
    }

    public boolean isHasAutoIncrementColumn() {

        for (ColumnMeta meta : columnMetas) {
            if (meta.isAutoIncrement()) {
                return true;
            }
        }

        return false;
    }

    public ColumnMeta getAutoIncrementColumn() {

        for (ColumnMeta meta : columnMetas) {
            if (meta.isAutoIncrement()) {
                return meta;
            }
        }

        return null;
    }

    public boolean isInPrimaryKeys(String name) {

        if (primaryKeys.contains(name)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
                .add("tableName", tableName)
                .add("columnMetas", columnMetas)
                .add("primaryKeys", primaryKeys)
                .add("prefixName", prefixName)
                .add("createTableSql", createTableSql)
                .toString();
    }
}
