package com.lianshang.generator.config;

import java.util.List;

/**
 * Created by walker on 16/2/3.
 */
public class ModuleConfig {

    private String groupName;

    private String moduleName;

    private String baseClassPackage;

    private DBConfig dbConfig;

    private String tablePrefix;

    private List<String> allowedTables;

    private List<String> exclusionTables;

    private String savePath;

    private boolean needDtoSubfix;

    private boolean isDependDp;

    private boolean needValidate;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getBaseClassPackage() {
        return baseClassPackage;
    }

    public void setBaseClassPackage(String baseClassPackage) {
        this.baseClassPackage = baseClassPackage;
    }

    public DBConfig getDbConfig() {
        return dbConfig;
    }

    public void setDbConfig(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public List<String> getAllowedTables() {
        return allowedTables;
    }

    public void setAllowedTables(List<String> allowedTables) {
        this.allowedTables = allowedTables;
    }

    public List<String> getExclusionTables() {
        return exclusionTables;
    }

    public void setExclusionTables(List<String> exclusionTables) {
        this.exclusionTables = exclusionTables;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public boolean isNeedDtoSubfix() {
        return needDtoSubfix;
    }

    public void setNeedDtoSubfix(boolean needDtoSubfix) {
        this.needDtoSubfix = needDtoSubfix;
    }

    public String getPrefixClassPackage() {
        return baseClassPackage + "." + moduleName;
    }

    public boolean isDependDp() {
        return isDependDp;
    }

    public void setIsDependDp(boolean isDependDp) {
        this.isDependDp = isDependDp;
    }

    public boolean isNeedValidate() {
        return needValidate;
    }

    public void setNeedValidate(boolean needValidate) {
        this.needValidate = needValidate;
    }
}
