package com.howie.story.biz.util;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 上午11:24 2018/8/3
 * @Modified by:xiaohaoyun
 */
public class BaseUtil {
    private BaseUtil () {}

    public static void traversArr (int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("~~数组为空");
            return;
        }

        for (int i = 0;i<arr.length; i++) {
            if (i == arr.length-1) {
                System.out.println(arr[i]);
            }else {
                System.out.print(arr[i]+" ->");
            }

        }

    }
}