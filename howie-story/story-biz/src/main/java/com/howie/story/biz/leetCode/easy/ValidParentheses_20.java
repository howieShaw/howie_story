package com.howie.story.biz.leetCode.easy;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by henry_shawn on 2017/4/4.
 */
public class ValidParentheses_20 {
    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string
     * is valid.

     The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     */
    private static final Stack<String> stack = new Stack<>();

    public static void main(String[] args) {
        String s = "()";
        System.out.println(valid(s));
    }

    public static boolean valid(String string){
        if ("".equals(string)) {
            return true;
        }
        if (string.length()%2 !=0) {
            return false;
        }

        boolean result = false;
        for (int i=0,len= string.length();i < len;i++) {
            char character = string.charAt(i);
            String right = Character.toString(character);
            if (")".equals(right) || "]".equals(right)
                    || "}".equals(right)) {

                if (stack.empty()) {
                    return false;
                }

                String left = stack.pop();
                if (left==null || "".equals(left)) {
                    return false;
                }
                String xy = left+right;
                if ("()".equals(xy) || "[]".equals(xy) || "{}".equals(xy)) {
                    result= true;
                } else {
                     return false;
                }
            } else {
                stack.push(right);
                result = false;
            }
        }

        return result;
    }


}
