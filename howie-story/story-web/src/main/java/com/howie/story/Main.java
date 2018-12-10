package com.howie.story;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午5:56 2018/9/7
 * @Modified by:xiaohaoyun
 */
public class Main {
    public static void main(String[] args) {
        double[] arr ={1,8,7,3,5,9};
        double sum = sum(arr);
        int d =(int)(sum % arr.length);
        System.out.println(d);
    }

    public static double sum (double[] arr) {
        double sum =0;
        for (int i = 0;i<arr.length;i++) {
            sum+=arr[i];
        }

        return sum;
    }

}
