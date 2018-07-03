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

    public static boolean isMobile (String mobile) {
//        Pattern pattern = Pattern.compile("(\\+\\d+)?1[3458]\\d{9}$");
        Pattern pattern = Pattern.compile("(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)");
        return pattern.matcher(mobile).matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("13818678989"));
    }
}
