package com.howie.story.biz.leetCode.easy;

/**
 * Created by henry_shawn on 2017/4/3.
 */
public class ReverseInteger_07 {
    /**
     * Reverse digits of an integer.

     Example1: x = 123, return 321
     Example2: x = -123, return -321

     */
    public static void main(String[] args) {
//        System.out.println((-143%100)/10);
        System.out.println(myReverseInteger(-14378));
    }
    public static int myReverseInteger(Integer num){
        int reverseNum = 0;
        while (num !=0 ) {
            if(reverseNum > Integer.MAX_VALUE || reverseNum < Integer.MIN_VALUE) {
                System.out.println("反转数超出整型范围");
                return -1;
            }
            int subNum = num % 10 ;
            reverseNum = reverseNum * 10 + subNum;
            num = num/10;
        }
        return reverseNum;
    }

}
