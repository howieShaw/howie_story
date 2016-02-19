package com.lianshang.generator.meta;

import com.google.common.base.MoreObjects;

/**
 * Created by walker on 16/2/3.
 */
public class ColumnMeta {

    private String columnName;

    private ColumnType columnType;

    private boolean isAutoIncrement;

    private int dataSize;

    private boolean nullable;

    private String comment;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    public boolean getNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isNullable() {
        return nullable;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
                .add("columnName", columnName)
                .add("columnType", columnType)
                .add("isAutoIncrement", isAutoIncrement)
                .add("dataSize", dataSize)
                .add("nullable", nullable)
                .add("comment", comment)
                .toString();
    }
}
