package com.howie.story.api.dto;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午3:51 2018/10/11
 * @Modified by:xiaohaoyun
 */
public class TableElementEntity {
    private String columnName;
    private String value;
    private String bgColor;
    private String width;

    public static TableElementEntity newElement (String columnName,String value,String bgColor,String width) {
        TableElementEntity entity = new TableElementEntity();
        entity.setBgColor(bgColor);
        entity.setColumnName(columnName);
        entity.setValue(value);
        entity.setWidth(width);

        return entity;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
