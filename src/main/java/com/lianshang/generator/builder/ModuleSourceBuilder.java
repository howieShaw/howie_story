package com.lianshang.generator.builder;

import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.TableMeta;

import java.util.List;

/**
 * Created by walker on 16/2/16.
 */
public class ModuleSourceBuilder {

    public static void build(ModuleConfig mc, DirectoryConfig dc, List<TableMeta> tableMetaList) throws ServiceException{

        for (TableMeta meta : tableMetaList) {

            TableSourceBuilder.build(mc, dc, meta);
        }
    }
}
