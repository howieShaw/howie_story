package com.howie.story.biz.leetCode.easy;

/**
 * Created by henry_shawn on 2017/9/3.
 */
public class BaseController {

    public static void printArray(int[] arr) {
        System.out.print("arr : { ");
        for (int i=0,len = arr.length; i < len;i++) {
            System.out.print(arr[i]);
            if (i != len - 1) {
                System.out.print(" ,");
            }

        }
        System.out.print(" }");
    }
}
