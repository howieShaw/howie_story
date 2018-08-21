package com.howie.story.biz.util;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午3:19 2018/8/4
 * @Modified by:xiaohaoyun
 */
public class reverseUtil {
    public static void main(String[] args) {
//        String string = "abcdefg";
//        System.out.println(reverseString(string));
        Integer num = -123456;
        System.out.println(reverseNumber(num));
    }


    /**
     *
     * @param str 字符串
     * @return 反转后的字符转
     */
    public static String reverseString (String str) {
        if (null == str||"".equals(str) || str.length()==1){
            return str;
        }
        StringBuilder result = new StringBuilder();
        for (int i=str.length() -1;i >=0;i--) {
            result.append(str.charAt(i));
        }

        return result.toString();
    }

    /**
     *
     * @param num 数字反转
     * @return
     */
    public static Integer reverseNumber (Integer num) {
        if (num==null) {
            return 0;
        }
        if (num > Integer.MAX_VALUE || num<Integer.MIN_VALUE) {
            System.out.println("超出整型范围");
            return -1;
        }
        Integer reverseNum = 0;
        while (num != 0) {
            int last = num % 10;
            reverseNum = reverseNum *10+last;
            num = num /10;
        }

        return reverseNum;

    }

}
