package com.howie.story.api.dto;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午3:55 2018/10/11
 * @Modified by:xiaohaoyun
 */
public class TableTop {
    private String topColumnName;
    private String bgColor;
    private String width;
    TableTop () {}


    public static TableTop newTableTop (String topColumnName,String bgColor,String width) {
        TableTop tableTop = new TableTop();
        tableTop.setBgColor(bgColor);
        tableTop.setTopColumnName(topColumnName);
        tableTop.setWidth(width);
        return tableTop;
    }

    public String getTopColumnName() {
        return topColumnName;
    }

    public void setTopColumnName(String topColumnName) {
        this.topColumnName = topColumnName;
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
