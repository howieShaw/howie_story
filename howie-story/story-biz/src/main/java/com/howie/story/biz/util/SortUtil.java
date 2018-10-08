package com.howie.story.biz.util;

/**
 * @Author:xiaohaoyun
 * @Description： 排序算法
 * @Date：created in 上午11:10 2018/8/3
 * @Modified by:xiaohaoyun
 */
public class SortUtil {

    public static void main(String[] args) {
        int[] arr = {8,9,7,6,5,3,4,2,1};
//        arr = insertSort(arr);
        quickSort(0,arr.length-1,arr);
        BaseUtil.traversArr(arr);
    }

    /**
     * 选择排序-从小到大，O(n^2)；选择排序是每次遍历都将寻找剩余数字中最小的那位，然后和当前数字比较。若剩余最小的数字小于当前数字则进行交换
     * @param arr
     * @return
     */
    public static int[] searchSort (int[] arr) {
        if (arr  == null || arr.length <= 0) {
            System.out.println("~~数组为空");
            return arr;
        }
        for (int i = 0;i<arr.length;i++) {
            for (int j = i+1;j<arr.length;j++) {
                int temp = arr[j];
                if (arr[i]>arr[j]) {
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

        return arr;

    }

    /**
     * 冒泡排序：冒泡排序是每次遍历比较相邻的两个数的大小，j 与 j+1 位。大的往数组最后放，小的放前，每次遍历能确定一个最大的数字位置。
     * @param arr
     * @return
     */
    public static int[] bubbleSort (int[] arr) {
        if (arr == null ||arr.length <= 0) {
            System.out.println("arr is null");
            return arr;
        }

        for (int i =0;i< arr.length -1;i++) {
            for (int j = 0;j<arr.length-1-i;j++) {
                int temp = arr[j];
                if (arr[j] > arr[j+1]) {
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        return arr;
    }

    /**
     * 插入排序 O(n)
     * @return
     */
    public static int[] insertSort (int[] arr) {
        if (arr == null || arr.length <=0) {
            return arr;
        }
        for (int i = 1;i <arr.length; i++) {
            int num = arr[i];
            int j = i-1;
            while (j>=0 && arr[j] > num) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = num;
        }

        return arr;

    }

    /**
     *  快速排序
     * @param arr
     * @return
     */
    public static void quickSort(int left,int right,int[] arr) {
        if (left>right) {
            return;
        }

        int base = arr[left];

        int pre = left;
        int last = right;

        while (pre != last) {
            //必先从后往前找比基准值小的数，保证last 在一轮查找至始至终比pre 大
            while (arr[last] >= base && pre < last) {
                last--;
            }

            //再从前往后找比基准值大的数
            while (arr[pre]<= base && pre < last) {
                pre++;
            }


            if (pre < last) {
                int temp = arr[pre];
                arr[pre] = arr[last];
                arr[last] = temp;
            }

        }
        // 最终pre = last 在中间位置相遇，交换基数的位置，这样pre 就是基数在排序中该有的位置
        arr[left] = arr[pre];
        arr[pre] = base;
        //将基数值
        quickSort(left,pre-1,arr);
        quickSort(pre+1,right,arr);

    }




}
