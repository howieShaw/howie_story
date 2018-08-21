package com.howie.story.biz.leetCode;

/**
 * Created by henry_shawn on 2017/4/3.
 */
public class Base {

    public static void traversal(int[] arr){
        if(arr == null){
            return;
        }
        System.out.print("[");
        for(int i = 0,len = arr.length;i < len;i++){
            if (i == len -1) {
                System.out.print(arr[i]);
            }else{
                System.out.print(arr[i]+" , ");
            }
        }
        System.out.print("]");
    }
}
