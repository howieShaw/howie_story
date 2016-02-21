package com.lianshang.generator.builder;

import com.lianshang.generator.config.DirectoryConfig;
import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.FileUtil;
import com.lianshang.generator.util.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by walker on 16/2/16.
 */
public class PomBuilder {

    public static void build(ModuleConfig mc, DirectoryConfig dc, List<TableMeta> tableMetaList) {

        String basicPath = StringUtil.getResourceBasicPath();

        String pomBasicPath = basicPath + "/pom";

        Map<String, String> replaceParams = new HashMap<>();
        replaceParams.put("[{]MODULE[}]", mc.getModuleName());
        replaceParams.put("[{]GROUP[}]", mc.getGroupName());

        String dependDp = "";
        if (mc.isDependDp()) {
            dependDp = Constant.RETURN;
            dependDp += FileUtil.loadFromFile(pomBasicPath + "/dp-denpend.xml");
        }
        replaceParams.put("[{]DEPEND-DP[}]", dependDp);

        boolean result = FileUtil.writeFile(dc.getModulePath() + "/pom.xml"
                , StringUtil.replace(FileUtil.loadFromFile(pomBasicPath + "/parent-pom.xml"), replaceParams));
        assert result;

        result = FileUtil.writeFile(dc.getModulePath() + "/" + mc.getModuleName()+ "-api/pom.xml"
                , StringUtil.replace(FileUtil.loadFromFile(pomBasicPath + "/api-pom.xml"), replaceParams));
        assert result;

        result = FileUtil.writeFile(dc.getModulePath() + "/" + mc.getModuleName()+ "-biz/pom.xml"
                , StringUtil.replace(FileUtil.loadFromFile(pomBasicPath + "/biz-pom.xml"), replaceParams));
        assert result;
    }

}
