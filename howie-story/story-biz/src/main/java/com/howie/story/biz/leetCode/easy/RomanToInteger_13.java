package com.howie.story.biz.leetCode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by henry_shawn on 2017/4/4.
 */
public class RomanToInteger_13 {
    /**
     * Given a roman numeral, convert it to an integer.

     Input is guaranteed to be within the range from 1 to 3999.
     将罗马数字1到3999转换为整型：
     【罗马数字】

     1~9: {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

     10~90: {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};

     100~900: {"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};

     1000~3000: {"M", "MM", "MMM"}.
     */

    public static void main(String[] args) {
        System.out.println(roman2Integer("LXXXIII"));
    }

    public static Integer roman2Integer(String romanNum){
        int sum = 0;
        if (romanNum.indexOf("IV") != -1) {
            sum =- 2;
        }
        if (romanNum.indexOf("IX") != -1) {
            sum =- 2;
        }
        if (romanNum.indexOf("XL") != -1) {
            sum =- 20;
        }
        if (romanNum.indexOf("XC") != -1) {
            sum =- 20;
        }
        if (romanNum.indexOf("CD") != -1) {
            sum =- 200;
        }
        if (romanNum.indexOf("CM") != -1) {
            sum =- 200;
        }
        Map<String,Integer> mapData = mockData();
        char[] charArray = romanNum.toCharArray();
        for (int i =0,len = charArray.length ; i < len;i++) {
           char a = charArray[i];
           String str = String.valueOf(a);
           Integer num = mapData.get(str);
           sum += num;
        }

        return sum;
    }

    public static Map<String,Integer> mockData(){
        Map<String ,Integer> map = new HashMap<String,Integer>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);

        return map;
    }
}
