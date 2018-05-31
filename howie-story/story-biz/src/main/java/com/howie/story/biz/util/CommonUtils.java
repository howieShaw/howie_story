package com.howie.story.biz.util;

import java.util.regex.Pattern;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午5:52 2018/5/25
 * @Modified by:xiaohaoyun
 */
public class CommonUtils {

    private CommonUtils () {}

    /**
     * 判断参数是否是数字类型
     * @param param 字符参数
     * @return  boolean
     */
    public static boolean isNumber (String param) {
        Pattern pattern = Pattern.compile("-?[0-9]*\\.?[0-9]*");

        return pattern.matcher(param).matches();
    }
}
