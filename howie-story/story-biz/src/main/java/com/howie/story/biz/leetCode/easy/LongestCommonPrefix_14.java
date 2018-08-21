package com.howie.story.biz.leetCode.easy;

import java.util.Arrays;

/**
 * Created by henry_shawn on 2017/4/4.
 */
public class LongestCommonPrefix_14 {
    /**
     *
     * Write a function to find the longest common prefix string amongst an array of strings.
     * 写一个方法查找数组中最长的先相同的前缀
     */

    public static void main(String[] args) {
       String prefix = findLongestPrefix("xiaoyao","xiaohaoyun");
       System.out.println("prefix : "+prefix);
       String[] arry = {"ab09880","abcde2398","abc8099","abcd09890","abc3476","abcd1234","abcde22145"};
       String arrPrefix = findLongestPrefix(arry);
       System.out.println("arrPrefix : "+ arrPrefix);

    }

    public static String findLongestPrefix(String[] arr){
        if (arr == null || arr.length < 1) {
            return "";
        }
        Arrays.sort(arr);
        String prefix = "";
        for (int i = 0;i < arr.length - 1;i++) {
            prefix = findLongestPrefix(arr[i],arr[i+1]);
        }
        return prefix;
    }

    public static String findLongestPrefix(String str1,String str2){
        String minStr = null;
        String maxStr = null;
        if (str1.length() < str2.length()) {
            minStr = str1;
            maxStr = str2;
        }else {
            maxStr = str1;
            minStr = str2;
        }
        for (int i = minStr.length() - 1;i >= 0;i--) {
            minStr = minStr.substring(0,i);
            if (maxStr.contains(minStr)) {
                break;
            }
        }

        return minStr;
    }


}
