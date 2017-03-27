package com.lianshang.generator.builder;

import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.util.FileUtil;

/**
 * Created by walker on 16/2/16.
 */
public class DirectoryBuilder {

    public static DirectoryConfig build(ModuleConfig config) throws ServiceException{

        String modulePath = config.getSavePath() + "/" + config.getModuleName();

//        if (FileUtil.isExistedDir(modulePath)) {
//            throw new ServiceException(-1, modulePath + " is existed!");
//        }

        FileUtil.deleteDir(modulePath);

        FileUtil.makeDir(modulePath);

        String apiSourcePrefixPath = modulePath + "/" + config.getModuleName() + "-api/src/main/java/"
                + getSourcePath(config.getBaseClassPackage()) + "/" + config.getModuleName() + "/api";
        FileUtil.makeDir(apiSourcePrefixPath);
        FileUtil.makeDir(apiSourcePrefixPath + "/" + "dto");
        FileUtil.makeDir(apiSourcePrefixPath + "/" + "service");

        String bizSourcePrefixPath = modulePath + "/" + config.getModuleName() + "-biz/src/main/java/"
                + getSourcePath(config.getBaseClassPackage()) + "/" + config.getModuleName()  + "/biz";
        FileUtil.makeDir(bizSourcePrefixPath);
        FileUtil.makeDir(bizSourcePrefixPath + "/dao");
        FileUtil.makeDir(bizSourcePrefixPath + "/entity");
        FileUtil.makeDir(bizSourcePrefixPath + "/service/impl");

        String bizResourcePrefixPath = modulePath + "/" + config.getModuleName() + "-biz/src/main/resources";
        FileUtil.makeDir(bizResourcePrefixPath);

        String bizTestSourcePrefixPath = modulePath + "/" + config.getModuleName() + "-biz/src/test/java/"
                + getSourcePath(config.getBaseClassPackage()) + "/" + config.getModuleName()  + "/biz";
        FileUtil.makeDir(bizTestSourcePrefixPath);

        String bizTestResourcePrefixPath = modulePath + "/" + config.getModuleName() + "-biz/src/test/resources";
        FileUtil.makeDir(bizTestResourcePrefixPath);

        String angularHtmlPath = modulePath + "/" + "webapp/app/" ;
        FileUtil.makeDir(angularHtmlPath);

        DirectoryConfig dc = new DirectoryConfig();
        dc.setModulePath(modulePath);
        dc.setApiSourcePrefixPath(apiSourcePrefixPath);
        dc.setBizSourcePrefixPath(bizSourcePrefixPath);
        dc.setBizResourcePrefixPath(bizResourcePrefixPath);
        dc.setBizTestSourcePrefixPath(bizTestSourcePrefixPath);
        dc.setBizTestResourcePrefixPath(bizTestResourcePrefixPath);
        dc.setAngularHtmlPath(angularHtmlPath);
        return dc;
    }

    private static String getSourcePath(String baseClassPackage) {

        return baseClassPackage.replace(".", "/");
    }
}
