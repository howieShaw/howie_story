package com.howie.story.biz.leetCode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by henry_shawn on 2017/8/29.
 */
public class RemoveDuplicatesFromSortedArray_26 {
    /**
     * Given a sorted array, remove the duplicates in place such that each element appear only once and return the
     * new length.

     Do not allocate extra space for another array, you must do this in place with constant memory.

     For example,
     Given input array nums = [1,1,2],

     Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
     It doesn't matter what you leave beyond the new length.
     给定一个 有序数组，删除数组里的重复元素，不能新建数组，返回数组元素个数，
     注：题目的return the new length 返回不是数组的长度，数组一旦创建长度是不变的，应该是返回去重后的数组元素的个数
     * */

    public static void main(String[] args) {
        int[] arr = {1,2,3,3,4,5,6,6,7};
        int count = removeDuplicatesNum(arr);
        System.out.println("count" + count);
        for (int i : arr) {
            System.out.print(i+" ");
        }
        List list = new ArrayList();
        list.remove(0);
    }

    public static int removeDuplicatesNum(int[] arr) {
        if (arr == null) {
            return -1;
        }
        int count = arr.length;
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0,len = arr.length;i < len;i++) {
            if (map.containsKey(arr[i])) {
                int j = i;
                int last = count-1;
                for (;j < last;j++) {
                    arr[j] = arr[j +1];
                }
                arr[last] = 0;
                count--;
            } else {
                map.put(arr[i],i);
            }
        }

        return count;
    }



}
