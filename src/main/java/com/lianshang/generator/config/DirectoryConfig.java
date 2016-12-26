package com.lianshang.generator.config;

/**
 * Created by walker on 16/2/16.
 */
public class DirectoryConfig {

    private String modulePath;

    private String apiSourcePrefixPath;

    private String bizSourcePrefixPath;

    private String bizResourcePrefixPath;

    private String bizTestSourcePrefixPath;

    private String bizTestResourcePrefixPath;

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    public String getApiSourcePrefixPath() {
        return apiSourcePrefixPath;
    }

    public void setApiSourcePrefixPath(String apiSourcePrefixPath) {
        this.apiSourcePrefixPath = apiSourcePrefixPath;
    }

    public String getBizSourcePrefixPath() {
        return bizSourcePrefixPath;
    }

    public void setBizSourcePrefixPath(String bizSourcePrefixPath) {
        this.bizSourcePrefixPath = bizSourcePrefixPath;
    }

    public String getBizResourcePrefixPath() {
        return bizResourcePrefixPath;
    }

    public void setBizResourcePrefixPath(String bizResourcePrefixPath) {
        this.bizResourcePrefixPath = bizResourcePrefixPath;
    }

    public String getBizTestSourcePrefixPath() {
        return bizTestSourcePrefixPath;
    }

    public void setBizTestSourcePrefixPath(String bizTestSourcePrefixPath) {
        this.bizTestSourcePrefixPath = bizTestSourcePrefixPath;
    }

    public String getBizTestResourcePrefixPath() {
        return bizTestResourcePrefixPath;
    }

    public void setBizTestResourcePrefixPath(String bizTestResourcePrefixPath) {
        this.bizTestResourcePrefixPath = bizTestResourcePrefixPath;
    }
}
