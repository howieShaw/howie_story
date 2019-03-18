package com.howie.story.biz.leetCode.offer;

import com.howie.story.biz.leetCode.Base;

public class MergeSortList extends Base {
    /**
     * 合并两个有序数组
     */

    public static void main(String[] args) {
        int[] arr1 = {1,3,5,7,9};
        int[] arr2 = {2,4,6,8,10,14,15};
        int[] arr = mergeList(arr1,arr2);
        traversal(arr);
    }

    /**
     *
     * @param arr1 有序数组1
     * @param arr2 有序数组2
     * @return 合并后的数组
     */
    public static int[] mergeList(int[] arr1,int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return null;
        }
        if (arr1 == null) {
            return  arr2;
        }
        if (arr2 == null) {
            return arr1;
        }
        int len = arr1.length + arr2.length;
        int[] newArr = new int[len];

        int index1 = 0;
        int index2 = 0;
        int index3 ;
        for ( index3=0;index3 < len ;index3++) {
            if (index1 >= arr1.length || index2 > arr2.length) {
                break;
            }
            if (arr1[index1] < arr2[index2]) {
                newArr[index3] = arr1[index1++];
            } else {
                newArr[index3] = arr2[index2++];
            }

        }
        while (index1 < arr1.length) {
            newArr[index3++] = arr1[index1++];
        }

        while (index2 < arr2.length) {
            newArr[index3++] = arr2[index2++];
        }

        return newArr;

    }


}
