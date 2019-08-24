package com.howie.story.api.util;


import com.howie.story.api.annotation.TableColumn;
import com.howie.story.api.bean.TableRule;
import com.howie.story.api.dto.TableElementEntity;
import com.howie.story.api.dto.TableEntity;
import com.howie.story.api.dto.TableRowEntity;
import com.howie.story.api.dto.TableTop;
import com.howie.story.api.enums.ColorEnum;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午3:01 2018/10/11
 * @Modified by:xiaohaoyun
 */
public class HtmlUtil {

    private HtmlUtil() {}

    public static final String TABLE_HAIL = "<table border=\"1\" bordercolor=\"#000000\" cellpadding=\"2\" cellspacing=\"0\" style=\"font-size: 12pt; border-collapse:collapse; border:none\">";
    public static final String TABLE_TAIL = "</table>";
    public static final String T_HEAD_HAIL = "<thead align=\"center\">";
    public static final String T_HEAD_TAIL = "</thead>";
    public static final String TR_TOP_HAIL = "<tr bgcolor=\"#add8e6\">";
    public static final String TR_HAIL_EVEN = "<tr bgcolor=\"#f0ffff\">";
    public static final String TR_FOOT_HAIL = "<tr bgcolor='#fffff0'>";
    public static final String TR_HAIL_ODD ="<tr>";
    public static final String TR_TAIL = "</tr>";
    public static final String T_BODY_HAIL = "<tbody align=\"center\">";
    public static final String T_BODY_TAIL = "</tbody>";
    public static final String TD_HAIL = "<td bgcolor=\"";
    public static final String TD_200_PX = "<td style=\"width:200px;\">";
    public static final String TD_TAIL = "</td>";
    public static final String TH_HAIL = "<th bgcolor=\"";
    public static final String TH_TAIL = "</th>";
    public static final String WIDTH = "\" width=\"";
    public static final String FINISH = "\">";
    public static final String BR = "<br/>";
    public static final String H_HAIL_3 = "<h3>";
    public static final String H_TAIL_3 = "</h3>";
    public static final String H_HAIL_2 = "<h2>";
    public static final String H_TAIL_2 = "</h2>";
    public static final String WIDTH_PX = "200";
    public static final String SPAN_HAIL = "<span style=\"color:";
    public static final String COLOR_CORAL = "coral";
    public static final String COLOR_SEA_GREEN = "lightseagreen";
    public static final String SPAN_TAIL = "</span>";
    public static final String TD_COL_SPAN = "<td colspan=\"%d\" style=\"width:200px;\">";

    public static final String TFOOT_HAIL= "<tfoot align=\"center\">";
    public static final String TFOOT_TAIL = "</tfoot>";
    public static final String TOTAL_DESC = "总计";

//    private static final List<String> INVALID_COLUMN = Arrays.asList("serialVersionUID");


    public static String createTable (TableEntity tableEntity) {
        if (tableEntity.getTableTops() == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(TABLE_HAIL);
        builder.append(T_HEAD_HAIL);
        builder.append(TR_TOP_HAIL);
        Map<String, TableTop> tableTops = tableEntity.getTableTops();
        StringBuilder topBuilder = new StringBuilder();
        for (Map.Entry<String,TableTop>  entry : tableTops.entrySet()) {
            topBuilder.delete(0,topBuilder.length());
            TableTop tableTop = entry.getValue();
            topBuilder.append(TH_HAIL);
            topBuilder.append(tableTop.getBgColor());
            topBuilder.append(WIDTH);
            topBuilder.append(tableTop.getWidth());
            topBuilder.append(FINISH);
            topBuilder.append(tableTop.getTopColumnName());
            topBuilder.append(TH_TAIL);
            builder.append(topBuilder.toString());
        }
        builder.append(TR_TAIL);

        if (CollectionUtils.isEmpty(tableEntity.getTableEntities())) {
            builder.append(T_HEAD_TAIL);
            builder.append(TABLE_TAIL);
            return builder.toString();
        }
        builder.append(T_BODY_HAIL);
        List<TableRowEntity> rowEntities = tableEntity.getTableEntities();

        for (TableRowEntity rowEntity : rowEntities) {
            if (rowEntity.getRow() % 2 == 0) {
                builder.append(TR_HAIL_EVEN);
            } else {
                builder.append(TR_HAIL_ODD);
            }

            Map<String, TableElementEntity> columnMap = rowEntity.getColumnValue();
            for (Map.Entry<String,TableTop>  entry : tableTops.entrySet()) {
                TableElementEntity column = columnMap.get(entry.getKey());
                builder.append(TD_HAIL);
                builder.append(column.getBgColor());
                builder.append(WIDTH);
                builder.append(column.getWidth());
                builder.append(FINISH);
                builder.append(column.getValue());
                builder.append(TD_TAIL);
            }
            builder.append(TR_TAIL);
        }
        builder.append(T_BODY_TAIL);
        if (tableEntity.getTableFoot() != null) {
            //开始统计页脚
            builder.append(TFOOT_HAIL);
            Map<String,Object> foot = tableEntity.getTableFoot();
            int footSize = foot.size();
            int topSize = tableTops.size();
            int colSpan = topSize - footSize;
            if (colSpan >0) {
                builder.append(TR_FOOT_HAIL);
                builder.append(String.format(TD_COL_SPAN,colSpan));
                builder.append(TOTAL_DESC);
                builder.append(TD_TAIL);
            }

            for (Map.Entry<String,TableTop> entry : tableTops.entrySet()) {
                if (!foot.containsKey(entry.getKey())) {
                    continue;
                }
                Object total = foot.get(entry.getKey());
                builder.append(TD_200_PX);
                builder.append(total);
                builder.append(TD_TAIL);
            }
            builder.append(TR_TAIL);

            builder.append(TFOOT_TAIL);
        }
        builder.append(T_HEAD_TAIL);
        builder.append(TABLE_TAIL);
        return builder.toString();
    }

    private static void bindTableEntity(StringBuilder builder, List<?> itemBOS) throws IllegalAccessException {
        TableEntity tableEntity = itemsToTable(itemBOS);
        String table = createTable(tableEntity);
        builder.append(table);
        builder.append(BR);
    }


    public static  TableEntity itemsToTable (List<?> itemBOS) throws  IllegalAccessException {
        return itemsToTable(itemBOS,null);
    }

    public static  TableEntity itemsToTable (List<?> itemBOS, TableRule colorRule) throws  IllegalAccessException {
        if (CollectionUtils.isEmpty(itemBOS)) {
            return null;
        }

        TableEntity table = new TableEntity();
        Map<String,TableTop> tops = new LinkedHashMap<String,TableTop>();

        List<TableRowEntity> rowEntities = new ArrayList<TableRowEntity>();
        boolean buildTop = false;
        int row = 1;
        Map<String,Object> foot = null;
        for (Object itemBO : itemBOS) {
            Field[] fields = itemBO.getClass().getDeclaredFields();
            Field.setAccessible(fields,true);
            TableRowEntity rowEntity = new TableRowEntity();
            rowEntity.setRow(row++);
            Map<String,TableElementEntity> entityMap = new HashMap<String,TableElementEntity>();
            for (int i=0;i < fields.length; i++) {
                Field field = fields[i];

                TableColumn tableColumn = field.getAnnotation(TableColumn.class);
                if (tableColumn == null || !tableColumn.show()) {
                    continue;
                }
                String columnName = tableColumn.columnName();
                if (!buildTop) {
                    TableTop top = TableTop.newTableTop(columnName,"",WIDTH_PX);
                    tops.put(tableColumn.columnKey(),top);
                }
                Object value = field.get(itemBO);
                String valueStr = String.valueOf(value);
                String color = "";
                if (colorRule != null ) {
                    ColorEnum colorEnum = colorRule.getColumnColor(tableColumn.columnKey(),field.get(itemBO));
                    color = colorEnum.getName();
                    valueStr = colorRule.dealFormatValue(tableColumn.columnKey(),field.get(itemBO));
                }
                TableElementEntity  element= TableElementEntity.newElement(tableColumn.columnKey(),valueStr,color,WIDTH_PX);
                entityMap.put(field.getName(),element);

                if (tableColumn.needTotal()) {
                    if (foot == null) {
                        foot = new HashMap<String,Object>();
                    }
                    if (foot.containsKey(tableColumn.columnKey())) {
                        Object obj = foot.get(tableColumn.columnKey());
                        if (obj instanceof Integer) {
                            Integer valueInt = (Integer) value;
                            Integer objValue = (Integer) obj;
                            foot.put(tableColumn.columnKey(),valueInt+objValue);
                        } else if (obj instanceof Long) {
                            Long valueLong = (Long) value;
                            Long objLong = (Long) obj;
                            foot.put(tableColumn.columnKey(),valueLong+objLong);
                        } else if (obj instanceof BigDecimal) {
                            BigDecimal valueBig = (BigDecimal) value;
                            BigDecimal objBig = (BigDecimal) obj;
                            foot.put(tableColumn.columnKey(),valueBig.add(objBig));
                        } else {
                            //非数字类型，不能进行叠加
                            foot.put(tableColumn.columnKey(),value);
                        }
                    } else {

                        foot.put(tableColumn.columnKey(),value);
                    }

                }
            }
            buildTop = true;
            rowEntity.setColumnValue(entityMap);
            rowEntities.add(rowEntity);
        }
        table.setTableTops(tops);

        table.setTableEntities(rowEntities);
        //处理页脚特殊字段
        if (foot != null) {
            table.setTableFoot(foot);
            if (colorRule != null) {
                colorRule.dealFormatFoot(foot);
            }
        }
        return table;
    }

    public static List<TableTop> getTableTop (String classPath) throws ClassNotFoundException {
        Field[] fields = Class.forName(classPath).getFields();
        List<TableTop> tops = new ArrayList<TableTop>();
        for (Field field : fields) {
            TableTop top = TableTop.newTableTop(field.getName(),"",WIDTH_PX);
            tops.add(top);
        }

        return tops;

    }
}
