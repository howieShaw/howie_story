package com.howie.story.biz.leetCode.offer;

import com.howie.story.biz.leetCode.Base;

/**
 * 求一个数组中 最小（大）K个数
 */
public class TopK extends Base {
    public static void main(String[] args) {
        int[] arr = {1,8,7,6,5,4,3,2,9};
        System.out.println("quickSort : ");
        Base.traversal(topKQuickSort(arr,3));
        System.out.println();
        System.out.println("heap : ");
        Base.traversal(topKHeap(arr,3));
    }



    /**
     * 使用快排的思想，将最arr[k] 做为基数，把比arr[k]小的数移到数组的左边，比它大的数移到右边
     * @param arr 乱序数组
     * @return 返回数组中比前k个最小的数
     */
    public static int[] topKQuickSort(int[] arr,int k) {

        int[] newArr = new int[k];
        int low = 0;
        int height = arr.length-1;
        int index = partion(arr,low,height);
        while (index !=k-1) {
            if (index > k-1) {
                height = index-1;
                index = partion(arr,low,height);
            } else {
                low = index+1;
                index =partion(arr,low,height);
            }
        }

        for (int i=k-1;i>=0;i--) {
            newArr[i] = arr[--k];
        }
        return newArr;
    }

    public static int partion(int[] arr,int low,int height) {

        int base = arr[low];
        int left = low;

        while (low < height) {
            while (arr[height] >= base && low < height) {
                height--;
            }
            while (arr[low]<= base && low <height) {
                low++;
            }
            if (low < height) {
                Base.swap(arr,low,height);
            }
        }
        Base.swap(arr,left,low);
        return low;
    }

    /**
     * 使用堆优先队列堆方式,将最小堆排上面
     */
    public static void buildHeap(int[] arr,int parent,int len) {
        int parentValue = arr[parent];
        int child = 2*parent+1;
        while (child < len) {
            int rightChild = child+1;
            //如果存在右节点，且右节点比左节点小，则先找右节点
            if (rightChild < len && arr[rightChild] < arr[child]) {
                child = rightChild;
            }
            //如果父节点已经比子节点小了直接中止
            if (parentValue < arr[child]) {
                break;
            }
            //到这里证明父节点比子节点大，将子节点上移
            arr[parent] = arr[child];
            parent = child;
            child = child*2+1;

        }
        arr[parent] = parentValue;

    }

    /**
     *
     * @param arr 乱序数组
     * @param k 占位数
     * @return 取数组中k个最小大数
     */
    public static int[] topKHeap (int[] arr,int k ) {
        //建立初始堆，将最小的移动到第一位
        for (int i = arr.length/2;i>=0;i--) {
            buildHeap(arr,i,arr.length);
        }

        int[] newArr = new int[k];
        //获取小的k个数
        int index =0;
        while (index <k) {
            int temp = arr[0];
            newArr[index] = temp;
            arr[0] = arr[arr.length-1-index];
            buildHeap(arr,0,arr.length-1-index);
            index++;
        }

        return newArr;
    }
}
