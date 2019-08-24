package com.howie.story.biz.leetCode.offer;

import com.howie.story.biz.leetCode.Base;

/**
 * 全排列
 */
public class Perm extends Base {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        permNum(arr,0,2);
    }

    public static void permNum(int[] arr,int begin,int end) {
        if (begin == end) {
            for (int i =0;i <=end;i++) {
                System.out.print(arr[i]+" ");
            }
            System.out.println(" ");
        } else {
            for(int j=begin;j<=end;j++){
                swap(arr,begin,j);  //for循环将begin~end中的每一个数放到begin位置中去
                permNum(arr,begin+1,end); //假设begin位置确定，那么对begin+1~end中的数组进行全排列
                swap(arr,begin,j); //换过去后再将数组还原
            }
        }
    }
}
