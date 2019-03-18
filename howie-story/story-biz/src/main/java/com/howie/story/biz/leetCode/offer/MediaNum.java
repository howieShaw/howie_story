package com.howie.story.biz.leetCode.offer;

import com.howie.story.biz.leetCode.Base;

/**
 *  题目找出一个数组中的中位数
 */
public class MediaNum extends Base {

    public static void main(String[] args) {
        int[] arr = {8,4,3,2,1,6,7,5};

       int mediaNum =  mediaNum(arr);
        System.out.println("中位数为："+mediaNum);
    }

    /**
     *  思考，方法1 将一个数组排序，排序完后arr.length /2 的数就是数组的中位数。
     *      方法2 ：通过方法1 可知一个数组的中位数就是排序完后数组中间的那个数，可以考虑快排的思想，将arr.length/2位置上的数作为基准数
     *      比它小的排放到它的左边，比它大的放到它的右边，最后找到这个基准数的位置，若这个位置等于arr.length/2 那么这个数就是中位数
     */

    /**
     *
     * @param arr 需要分区的数组
     * @param low 低位 一般是数组的初始位
     * @param high 高位 一般是数组的最大位置
     * @return 基准位的位置
     */
    public static int partetion(int[] arr,int low,int high) {
        int base = arr[low];
        int left = low;
        while (left != high) {
            while (left<high && arr[high] >= base) {
                high--;
            }
            while (left < high && arr[left] <= base) {
                left++;
            }
            if (left < high) {
                Base.swap(arr,left,high);
            }
        }
        Base.swap(arr,low,left);

        return left;
    }

    /**
     *
     * @param arr
     * return 找出数组中的中位数
     */
    public static int mediaNum(int[] arr) {
        if (arr == null) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int low = 0;
        int high = arr.length-1;
        int index = partetion(arr,low,high);
        int mid = arr.length/2;
        while (index != mid) {
            if (index > mid) {
                high--;
                index = partetion(arr,low,high);
            }
            if (index < mid) {
                low++;
                index=partetion(arr,low,high);
            }
        }

        return arr[mid];

    }
}
