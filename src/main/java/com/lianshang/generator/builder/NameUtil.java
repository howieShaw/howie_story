package com.lianshang.generator.builder;

import com.lianshang.generator.meta.TableMeta;

/**
 * Created by walker on 16/2/17.
 */
public class NameUtil {

    public static String getDtoClassName(boolean needDtoSuffix, TableMeta meta) {

        return meta.getPrefixName() + (needDtoSuffix?"DTO":"");
    }

    public static String getEntityClassName(TableMeta meta) {

        return meta.getPrefixName() + "Entity";
    }

    public static String getServiceInterfaceName(TableMeta meta) {

        return meta.getPrefixName() + "Service";
    }

    public static String getDaoInterfaceName(TableMeta meta) {

        return meta.getPrefixName() + "Dao";
    }

    public static String getServiceImplClassName(TableMeta meta) {

        return meta.getPrefixName() + "ServiceImpl";
    }

    public static String getServiceTestClassName(TableMeta meta) {

        return meta.getPrefixName() + "ServiceTest";
    }
}
