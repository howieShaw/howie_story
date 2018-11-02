package com.howie.story.api.dto;

import java.util.Map;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午3:33 2018/10/11
 * @Modified by:xiaohaoyun
 */
public class TableRowEntity {
    private int row;
    private Map<String,TableElementEntity> columnValue;

    public static TableRowEntity newTableRow (int row,Map<String,TableElementEntity> columnValue) {
        TableRowEntity rowEntity = new TableRowEntity();
        rowEntity.setRow(row);
        rowEntity.setColumnValue(columnValue);
        return rowEntity;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Map<String, TableElementEntity> getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(Map<String, TableElementEntity> columnValue) {
        this.columnValue = columnValue;
    }
}
