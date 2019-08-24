package com.howie.story.biz.leetCode.medium;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.util.*;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * 返回字符串中最长不包含重复字母的字串
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        int result = LengthSolution.lengthOfLongestSubstring("bbbbb");
        System.out.println("result : "+result);
    }


}

class LengthSolution {
    public static int lengthOfLongestSubstring(String s) {

        if (s == null) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        //最长字串的长度
        int maxlen =0;

        char[] arr = s.toCharArray();

        //用map 来存放字符位置（最后出现的位置）
        Map<Character,Integer> map = new HashMap<Character, Integer>();

        int leftIndex = -1;
        for(int i =0,len = arr.length;i<len;i++) {

            if (map.containsKey(arr[i])) {
                Integer index = map.get(arr[i]);
                leftIndex = Math.max(leftIndex,index);
            }
            map.put(arr[i],i);
            maxlen = Math.max(maxlen,i-leftIndex);
        }

        return maxlen;


    }

    public static String LongestSubstring(String s) {

        if (s == null) {
            return "";
        }

        if (s.length() == 1) {
            return s;
        }


        return "";
    }

    /**
     * 网上解答，这个效率更快，因为是用空间换时间的方式
     */

    public static int lengthOfLongestSubstringByIntl(String s) {

        if (s == null) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        int[] m = new int[256];
        Arrays.fill(m, -1);

        int maxLen = 0, leftIndex = -1;
        for (int i = 0; i < s.length(); ++i) {
            //将左指针指向元素下标（若是重复元素，及重复元素的下标）
            leftIndex = Math.max(leftIndex, m[s.charAt(i)]);
            m[s.charAt(i)] = i;
            maxLen = Math.max(maxLen, i - leftIndex);
        }
        return maxLen;
    }



    /**
     *  最长子前缀
     * @param s
     * @return
     */
    public static int lengthOfPreLongestSubstring(String s) {

        if (s == null) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        int len = s.length();
        String[] arr = new String[len];
        for (int i=s.length()-1;i>=0;i--) {
            arr[i] = s.substring(0, i);
        }

        int maxlen =0;
        for (int i=0;i<len;i++) {
            String sub = arr[i];

            char[] subChars = sub.toCharArray();
            Map<Character,Integer> map = new HashMap<Character,Integer>();
            boolean isNotRepeat = true;
            for (int j =0;j<subChars.length;j++) {
                if (map.containsKey(subChars[j])) {
                    isNotRepeat = false;
                    break;
                } else {
                    map.put(subChars[j],1);
                }
            }

            if (isNotRepeat) {
                maxlen = maxlen > sub.length() ? maxlen : sub.length();
            }

        }

        return maxlen;
    }
}
