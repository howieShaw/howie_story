package com.lianshang.generator.util;

import jodd.util.CharUtil;

import java.util.Map;

/**
 * Created by walker on 16/2/4.
 */
public class StringUtil {

    public static boolean isEmpty(String str) {

        if (str == null || str.length() == 0) {
            return true;
        }

        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static char getLowerChar(char ch) {

        return CharUtil.toLowerAscii(ch);
    }

    public static char getUpperChar(char ch) {
        return CharUtil.toUpperAscii(ch);
    }

    public static boolean isLowerChar(char ch) {

        return CharUtil.isLowercaseAlpha(ch);
    }

    public static boolean isUpperChar(char ch) {

        return CharUtil.isUppercaseAlpha(ch);
    }

    public static String toUpperForFirstChar(String str) {

        if (isEmpty(str)) {
            return str;
        }

        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    public static String toLowerForFirstChar(String str) {

        if (isEmpty(str)) {
            return str;
        }

        return str.substring(0,1).toLowerCase() + str.substring(1);
    }

    public static String getResourceBasicPath() {

        return Thread.currentThread().getContextClassLoader().getResource("origin-files").getPath();
    }

    public static String replace(String content, Map<String, String> params) {

        for (Map.Entry<String, String> entry : params.entrySet()) {

            content = content.replaceAll(entry.getKey(), entry.getValue());
        }

        return content;
    }
}
