package com.howie.story.biz.leetCode.Hard;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Description 求两个数组中的中位数
 * @Author howie
 * @Date 2020/4/24 4:41 下午
 */

public class MedianOfTwoArray_4 {
    /**
     * @lc app=leetcode id=4 lang=java
     *
     * [4] Median of Two Sorted Arrays
     *
     * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
     *
     * algorithms
     * Hard (25.34%)
     * Total Accepted:    394.2K
     * Total Submissions: 1.5M
     * Testcase Example:  '[1,3]\n[2]'
     *
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     *
     * Find the median of the two sorted arrays. The overall run time complexity
     * should be O(log (m+n)).
     *
     * You may assume nums1 and nums2 cannot be both empty.
     *
     * Example 1:
     *
     *
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * The median is 2.0
     *
     *
     * Example 2:
     *
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * The median is (2 + 3)/2 = 2.5
     *
     *
     */
    public static void main(String[] args) {
        int[] arr1 = {1,3,5,7,9};
        int[] arr2 = {2,4,6,8,10};
        MedianOfTwoArray_4 median = new MedianOfTwoArray_4();

        System.out.println(median.findMedianSortedArrays(arr1,arr2));
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //方法1：将两个数组合并排序，后取中位数
        int[] num3 = merge(nums1,nums2);
        if (num3 == null) {
            return 0;
        }
        double result =0;
        int len = num3.length;

        if (len % 2 == 0) {
            //偶数
            int index = (len)/2-1;
            System.out.println("偶数中间1："+num3[index]);
            System.out.println("偶数中间2："+num3[index+1]);
            result = (num3[index] + num3[index+1]+0.0)/2;
            System.out.println("偶数的index:"+index);
        } else {
            //奇数
            int index = (len)/2;
            result = num3[index];
        }

        return result;
    }

    public int[] merge (int[] nums1,int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return new int[0];
        }
        if (nums1 == null) {
            return nums2;
        }
        if (nums2 == null) {
            return nums1;
        }
        int[] nums3 = new int[nums1.length+nums2.length];
        int index1 = 0;
        int index2 = 0;
        int index3 = 0;

        while (index1 <nums1.length && index2<nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                nums3[index3++] = nums1[index1++];
            } else {
                nums3[index3++] = nums2[index2++];
            }
        }

        while (index1<nums1.length) {
            nums3[index3++] = nums1[index1++];
        }

        while (index2 < nums2.length) {
            nums3[index3++] = nums2[index2++];
        }
        System.out.println("合并后："+ Arrays.toString(nums3));
        return nums3;

    }
}
