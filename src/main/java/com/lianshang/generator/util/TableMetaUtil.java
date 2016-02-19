package com.lianshang.generator.util;

import com.lianshang.generator.meta.TableMeta;

/**
 * Created by walker on 16/2/17.
 */
public class TableMetaUtil {

    public static boolean hasLoadFunction(TableMeta meta) {

        if (meta.getPrimaryKeys().size() > 0) {
            return true;
        }

        return false;
    }

    public static boolean hasUpdateFunction(TableMeta meta) {

        return hasLoadFunction(meta);
    }
}
