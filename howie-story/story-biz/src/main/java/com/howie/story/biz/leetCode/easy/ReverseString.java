package com.howie.story.biz.leetCode.easy;

/**
 * Created by henry_shawn on 2017/4/3.
 */
public class ReverseString {
    /**
     * 字符串反转
     */
    public static void main(String[] args) {
        String string = "AbcDefG";
        System.out.println("reverseOne : "+reverseOne(string));
        System.out.println("reverseTwo : "+reverseTwo(string));
        System.out.println("reverseThree : "+reverseThree(string));
    }

    /**
     * NO1： 用递归翻转
     */
    public static String reverseOne(String string){
        int len = string.length();
        if (len <= 1) {
            return string;
        }
        String subLeft = string.substring(0,len/2);
        String subRight = string.substring(len/2,len);
        return reverseOne(subRight) + reverseOne(subLeft);
    }

    /**
     * NO2:
     */
    public static String reverseTwo(String string){
        int length = string.length();
        String result = "";
        for (int i = length -1;i >= 0;i--) {
            result = result + string.charAt(i);
        }
        return result;
    }

    /**
     * NO3:
     */
    public static String reverseThree(String string){
       char[] array = string.toCharArray();
       String reverse = "";
       for (int i = array.length -1;i >= 0 ;i--) {
           reverse = reverse + array[i];
       }
       return reverse;
    }
    /**
     * NO4:
     */
    public static String reverseFour(String string){

        return new StringBuilder(string).reverse().toString();

    }
}
