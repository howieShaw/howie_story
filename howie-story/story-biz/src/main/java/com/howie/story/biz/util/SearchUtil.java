package com.howie.story.biz.util;

/**
 * @Author:xiaohaoyun
 * @Description： 查找
 * @Date：created in 下午3:08 2018/8/3
 * @Modified by:xiaohaoyun
 */
public class SearchUtil {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        int index = binarySearch(arr,3);
        System.out.println(index);
    }

    /**
     *
     * @param arr 有序数组
     * @param key 目标元素
     * @return 目标元素下标
     */
    public static int binarySearch (int[] arr,int key) {
        if (arr == null || arr.length <= 0) {
            return -1;
        }
        int min = 0;
        int max = arr.length;
        int midIndex =0;
        while (min < max) {
            midIndex = (min +max)/2;
            if (key > arr[midIndex]) {
                min++;
            }else if (key < arr[midIndex]) {
                max--;
            } else {
                break;
            }
        }


        return midIndex;
    }
}
