package com.howie.story.biz.util;

import com.howie.story.api.bean.SNode;
import com.howie.story.biz.leetCode.Base;

/**
 * @Author:xiaohaoyun
 * @Description： 排序算法
 * @Date：created in 上午11:10 2018/8/3
 * @Modified by:xiaohaoyun
 */
public class SortUtil {

    public static void main(String[] args) {
        int[] arr = {8,9,7,6,5,3,4,2};
//        arr = insertSort(arr);
//        quickSort(0,arr.length-1,arr);

//        int[] arr = bucketSort(mockSnode(),5);
        heapSort(arr);
//        mergeSort(arr);
        BaseUtil.traversArr(arr);
    }

    private static SNode<Integer>[] mockSnode () {
        SNode[] sNodes = new SNode[9];
        SNode<Integer> node1 = new SNode<Integer>(10,null);
        sNodes[0] = node1;
        SNode<Integer> node2 = new SNode<Integer>(19,null);
        sNodes[1] = node2;
        SNode<Integer> node3 = new SNode<Integer>(8,null);
        sNodes[2] = node3;
        SNode<Integer> node4 = new SNode<Integer>(15,null);
        sNodes[3] = node4;
        SNode<Integer> node5 = new SNode<Integer>(13,null);
        sNodes[4] = node5;
        SNode<Integer> node6 = new SNode<Integer>(21,null);
        sNodes[5] = node6;
        SNode<Integer> node7 = new SNode<Integer>(17,null);
        sNodes[6] = node7;
        SNode<Integer> node8 = new SNode<Integer>(28,null);
        sNodes[7] = node8;
        SNode<Integer> node9 = new SNode<Integer>(39,null);
        sNodes[8] = node9;

        return sNodes;
    }

    /**
     * 选择排序-从小到大，O(n^2)；选择排序是每次遍历都将寻找剩余数字中最小的那位，然后和当前数字比较。若剩余最小的数字小于当前数字则进行交换
     * @param arr
     * @return
     */
    public static int[] chooseSort (int[] arr) {
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

    /**
     * 桶排序：传入一个数组和一个桶大小的参数，通过数组中最大值和最小值计算每个桶存放数据的范围：（max-min+1）/桶数量。
     */

    public static int[] bucketSort(SNode<Integer>[] arr, int bucketNum) {
        int len = arr.length;

        if (bucketNum <= 0) {
            bucketNum = 10;
        }

        int min = arr[0].data;
        int max = arr[0].data;
        //找出数组中的最小值和最大值
        for (int i = 0;i<len;i++) {
            min = Math.min(arr[i].data,min);
            max = Math.max(arr[i].data,max);
        }

        //计算每个桶的容量空间，
        int space = (int)Math.ceil((max-min)/bucketNum)+1;
        SNode<Integer>[] buckets = new SNode[bucketNum];

        for (int j =0;j < len; j++) {
            int data = arr[j].data;
            //计算值应该在哪个桶内
            int index = (int)Math.floor((data - min)/space);
            SNode<Integer> sNode = buckets[index];
            if (sNode == null) {
                //桶内没有数据，直接赋值
                buckets[index] = arr[j];
            } else {
                //桶里存在值，比较值大小进行排序
                if (sNode.data >=arr[j].data) {
                    //桶里的第一个值比新值大，排在新值后面
                    arr[j].next = sNode;
                    buckets[index] = arr[j];
                } else  {
                    //桶里的第一个值比新值笑，遍历链表找到值比新值大的进行排序，没有新值就排在最后
                    SNode<Integer> afterNode = sNode.next;
                    SNode<Integer> pre = sNode;
                    while (afterNode != null) {
                        if (afterNode.data >= arr[j].data) {
                            pre.next = arr[j];
                            arr[j].next = afterNode;
                            break;
                        } else {
                            pre = afterNode;
                            afterNode = afterNode.next;
                        }
                    }
                    pre.next = arr[j];
                }
            }

        }

        //打印桶里的值
        int[] dataArr = new int[len];
        int index = 0;
        for (int i =0, buckLen = buckets.length;i <buckLen;i++) {
            SNode<Integer> node = buckets[i];
            while (node != null) {
                dataArr[index] = node.data;
                System.out.print(node.data+"-");
                node = node.next;
                index++;
            }
        }

        return dataArr;
    }

    public static void heapSort (int[] arr) {

        //将数组建立为初始堆，这里为什么是length/2 因为，
        for (int i = arr.length/2;i>=0;i--) {
            buildHeapDown(arr,i,arr.length);
        }

        //开始取出堆顶的值，将堆顶和堆位的值进行交换，重新进行堆变换
        for (int i = arr.length -1;i>=0;i--) {
            BaseUtil.swap(arr,0,i);

            buildHeapDown(arr,0,i);

        }
    }

    /**
     *
     * @param arr 重上往下构建堆
     * @param parent
     * @param len
     */
    public static void buildHeapDown (int[] arr,int parent,int len) {
        int parentValue = arr[parent];//先保持父节点的值
        int childIndex = 2*parent+1;//先查看左节点

        while (childIndex < len) {
            int rightIndex = childIndex +1;
            //如果右节点存在，且右节点的值比左节点大，则先匹配右节点
            if (rightIndex <len && arr[childIndex] < arr[rightIndex]) {
                childIndex = rightIndex;
            }
            //如果父节点的值比子节点的大，则直接终止
            if (parentValue > arr[childIndex]) {
                break;
            }

            //到这里证明父节点的值比子节点的小，将子节点的值往父节点移动,将子节点移动到下一个左节点
            arr[parent] = arr[childIndex];
//            arr[childIndex] = parentValue;
            parent = childIndex;
            childIndex = 2*childIndex+1;

        }
        //这里的parent已经是原来父节点的子结点了，这里将原来父节点的值赋值到数字该到到位置。
        arr[parent] = parentValue;

    }

    /**
     *
     * @param arr 一个无序数组
     * @param k 数组中K个最大大数
     * @return 返回数组中K个最大大数
     */
    public int[] topK(int[] arr,int k) {


        return null;
    }

    /**
     * `````````归并排序
     */

    public static void mergeSort(int[] arr) {
        mergeSort(arr,0,arr.length-1);
    }

    public static void mergeSort(int[] arr,int left,int right) {
        if (left >= right) {
            return;
        }
        int mid = (left+right)/2;
        mergeSort(arr,left,mid);//左边归并排序，使得左子序列有序
        mergeSort(arr,mid+1,right);//右边归并排序，使得右子序列有序
        mergeSort(arr,left,mid,right);//合并两个子序列
    }

    public static void mergeSort(int[] arr,int left,int mid,int right) {
        if (left > right) {
            return;
        }

        int low = left;
        int rStart = mid+1;
        int index = 0;
        int lIndex = left;
        int[] temp = new int[arr.length];

        while (low < mid && rStart <right) {
            if (arr[left] <= arr[rStart]) {
                temp[index++] = arr[low++];
            } else {
                temp[index++] = arr[rStart++];
            }
        }

        while (low < mid) {
            temp[index++] = arr[low++];
        }

        while (rStart < right) {
            temp[index++] = arr[rStart++];
        }

        //将临时数组的数据全拷贝到原数组
        //从临时数组拷贝到原数组
        while(lIndex<=right){
            arr[lIndex]=temp[lIndex];
            lIndex++;
        }

    }




}
