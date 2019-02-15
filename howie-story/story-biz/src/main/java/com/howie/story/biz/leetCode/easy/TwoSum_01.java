package com.howie.story.biz.leetCode.easy;


import com.howie.story.biz.leetCode.Base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by henry_shawn on 2017/4/3.
 */
public class TwoSum_01 extends Base {
    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

     You may assume that each input would have exactly one solution, and you may not use the same element twice.

     Example:
     Given nums = [2, 7, 11, 15], target = 9,

     Because nums[0] + nums[1] = 2 + 7 = 9,
     return [0, 1].
     */

    public static void main(String[] args) {
        int[] arr = {-3,4,3,90};
        int target = 0;
        int[] result = myTwoSum(arr,target);
        traversal(result);
    }

    public static int[] myTwoSum(int[] arr,int target){
        if (arr == null) {
            return null;
        }
        int[] result = new int[2];
        int len = arr.length;
        if (len == 1 ) {
            if (arr[0] == target) {
                result[0] = 0;
                result[1] = 0;
                return result;
            }else {
                return null;
            }
        }
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int i=0;i < len;i++) {
            int otherValue = target - arr[i];
            if (map.containsKey(otherValue)){
                result[1] = i;
                int firstIndex = map.get(otherValue);
                result[0] = firstIndex;
                return result;
            }else {
                map.put(arr[i],i);
            }
        }
        return null;
    }
}
