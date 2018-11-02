package com.howie.story.api.bean;


import com.howie.story.api.enums.ColorEnum;

import java.util.Map;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 4:20 PM 2018/10/30
 * @Modified by:xiaohaoyun
 */
public interface TableRule {

    /**
     *  字段颜色处理
     * @param columnKey 字段key
     * @param value  字段的值
     * @return 返回颜色
     */
    ColorEnum getColumnColor(String columnKey, Object value);

    /**
     * 字段值的格式处理
     * @param columnKey 字段key
     * @param value 字段的值
     * @return 返回处理后的值
     */
    String dealFormatValue(String columnKey, Object value);

    /**
     *
     * @param foot 处理表的页脚
     */
    void dealFormatFoot(Map<String, Object> foot);

}
