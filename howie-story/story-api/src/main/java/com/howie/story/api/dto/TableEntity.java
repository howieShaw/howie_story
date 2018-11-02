package com.howie.story.api.dto;

import java.util.List;
import java.util.Map;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午3:30 2018/10/11
 * @Modified by:xiaohaoyun
 */
public class TableEntity {
    private Map<String,TableTop> tableTops;

    private List<TableRowEntity> tableEntities;

    private Map<String,Object> tableFoot;

    public Map<String, TableTop> getTableTops() {
        return tableTops;
    }

    public void setTableTops(Map<String, TableTop> tableTops) {
        this.tableTops = tableTops;
    }

    public List<TableRowEntity> getTableEntities() {
        return tableEntities;
    }

    public void setTableEntities(List<TableRowEntity> tableEntities) {
        this.tableEntities = tableEntities;
    }

    public Map<String, Object> getTableFoot() {
        return tableFoot;
    }

    public void setTableFoot(Map<String, Object> tableFoot) {
        this.tableFoot = tableFoot;
    }
}
