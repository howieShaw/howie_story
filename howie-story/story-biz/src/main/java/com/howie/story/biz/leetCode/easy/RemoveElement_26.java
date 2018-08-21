package com.howie.story.biz.leetCode.easy;

/**
 * Created by henry_shawn on 2017/9/2.
 */
public class RemoveElement_26 {
    /**
     * Given an array and a value, remove all instances of that value in place and return the new length.

     Do not allocate extra space for another array, you must do this in place with constant memory.

     The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     给定一个数组和一个值，删除数组里与这个值相同的元素，并且返回数组新的长度。
     * */


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,3};
        int newlen = removeSameElementV2(arr,3);
        System.out.println("new len : " + newlen);
        BaseController.printArray(arr);
    }

    public static int removeSameElement (int[] arr,int value) {
        if (arr == null) {
            return -1;
        }
        int lastCount = arr.length - 1;
        for (int i = 0,len = arr.length;i < len; i++) {
            if (arr[i] == value) {
                for (int j = i;j<len-1;j++) {
                    arr[j] = arr[j+1];
                }
                arr[lastCount--] = 0;
            }
        }

        return lastCount;
    }

    public static int removeSameElementV2 (int[] arr,int value) {
        if (arr == null) {
            return -1;
        }
        int len =arr.length;
        int index =0;
        for (int i = index;i<len; i++) {
            if (arr[i] != value) {
                arr[index++] = arr[i];
            }
        }

        for (int i = index;i <len;i++ ) {
            arr[i] = 0;
        }
        return index;
    }
}

