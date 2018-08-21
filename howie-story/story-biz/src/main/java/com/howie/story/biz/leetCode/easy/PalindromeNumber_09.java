package com.howie.story.biz.leetCode.easy;

/**
 * Created by henry_shawn on 2017/4/3.
 */
public class PalindromeNumber_09 {
    /**
     * Determine whether an integer is a palindrome. Do this without extra space.

     Some hints:
     Could negative integers be palindromes? (ie, -1)

     If you are thinking of converting the integer to string, note the restriction of using extra space.

     You could also try reversing an integer. However, if you have solved the problem "Reverse Integer",
     you know that the reversed integer might overflow. How would you handle such case?
     */

    public static void main(String[] args) {
        System.out.println("isPalindromeNumber : "+ isPalindromeNumber(123454321));
        System.out.println("isPalindromeNumberTWO : " + isPalindromeNumberTWO(123454321));
    }
    public static boolean isPalindromeNumber(int num){
        int temp =num;
        Integer reverseResult = getIntegerReverse(temp);
        if (reverseResult.equals(num)) {
            return true;
        }

        return false;
    }

    private static Integer getIntegerReverse(int temp) {
        Integer reverseResult = 0;
        while (temp != 0) {
            int sub = temp % 10;
            reverseResult = reverseResult*10 +sub;
            temp = temp /10;
        }
        return reverseResult;
    }

    public static boolean isPalindromeNumberTWO(Integer num){

        String numStr = num.toString();
        int midIndex = numStr.length()/2;
        String left = numStr.substring(0,midIndex+1);
        left = reverseStr(left);
        String right = numStr.substring(midIndex,numStr.length());
        if (right.equals(left)) {
            return true;
        }

        return false;
    }

    public static String reverseStr(String string){
        int len = string.length();
        if (len <= 1) {
            return string;
        }
        String left = string.substring(0,len/2);
        String right = string.substring(len/2,len);
        return reverseStr(right) + reverseStr(left);
    }


}
