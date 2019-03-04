package com.howie.story.biz.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:xiaohaoyun
 * @Description： 查找
 * @Date：created in 下午3:08 2018/8/3
 * @Modified by:xiaohaoyun
 */
public class SearchUtil {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        int index = insertValueSearch(arr,3);
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

    /**
     * 插值查找，根据二分查找由来
     * @param arr 有序数组
     * @param key 目标值
     * @return 目标值的下标
     */
    public static int insertValueSearch(int[] arr, int key) {
        if (arr == null || arr.length ==0) {
            return -1;
        }
        if (arr.length == 1 && key == arr[0]) {
            return 0;
        }
        int min = 0;
        int max = arr.length-1;
        int mid  = 0;
        while (min <max) {
            //mid = (max +min)/2 = min + (max-min)/2
            //将上述的比例参数1/2改进为自适应的，根据关键字在整个有序表中所处的位置，让mid值的变化更靠近关键字key，这样也就间接地减少了比较次数。
            mid = min + (key - arr[min])/(arr[max] - arr[min]) * (max-min);
            if (arr[mid] < key) {
                min++;
            } else if (arr[mid] > key) {
                max--;
            } else {
                break;
            }
        }

        if (arr[mid]== key) {
            return mid;
        }

        return -1;
    }
}
