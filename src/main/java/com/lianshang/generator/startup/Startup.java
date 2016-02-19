package com.lianshang.generator.startup;

import com.google.common.collect.ImmutableList;
import com.lianshang.generator.builder.*;
import com.lianshang.generator.config.DBConfig;
import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.MetaLoader;
import com.lianshang.generator.meta.TableMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walker on 16/2/16.
 */
public class Startup {

    public static void main(String[] args) throws ServiceException {

        ModuleConfig mc = getModuleConfig();

        List<TableMeta> tableMetaList = MetaLoader.load(mc);
        assert tableMetaList.size() > 0;
        System.out.println("finish load table meta infos");

        DirectoryConfig dc = DirectoryBuilder.build(mc);
        System.out.println("finish directory build");

        ModuleSourceBuilder.build(mc, dc, tableMetaList);
        System.out.println("finish module source build");

        PomBuilder.build(mc, dc, tableMetaList);
        System.out.println("finish pom build");

        SpringBuilder.build(mc, dc, tableMetaList);
        System.out.println("finish spring build");

        SqlmapBuilder.build(mc, dc, tableMetaList);
        System.out.println("finish sqlmap build");

        TestBuilder.build(mc, dc, tableMetaList);
        System.out.println("finish test build");
    }

    private static ModuleConfig getModuleConfig() {

        DBConfig dbConfig = new DBConfig();
        dbConfig.setIp("localhost");
        dbConfig.setPort(3306);
        dbConfig.setDatabase("lianshang");
        dbConfig.setUsername("root");
        dbConfig.setPassword("123456");

//        dbConfig.setIp("192.168.1.229");
//        dbConfig.setPort(3306);
//        dbConfig.setDatabase("lianshang");
//        dbConfig.setUsername("dbadmin");
//        dbConfig.setPassword("lsdbdev147");

        ModuleConfig config = new ModuleConfig();
        config.setModuleName("com.lianshang");
        config.setModuleName("pc");
        config.setGroupName("pc");
        config.setBaseClassPackage("com.lianshang");
        config.setTablePrefix("pc_");
//        config.setAllowedTables(ImmutableList.of("pc_coupon"));
        config.setExclusionTables(new ArrayList<String>());
        config.setDbConfig(dbConfig);
        config.setSavePath("/Users/walker/Desktop");
        config.setIsDependDp(false);

        return config;
    }

}
