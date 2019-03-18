package com.howie.story.biz.leetCode.offer;

/**
 * 斐波拉契数列 从第三位数起，f（n）= f(n-1)+f(n-2)
 * 如：1 1 2 3 5 8 13 21 ...
 */
public class FeiBoLaQie {
    public static void main(String[] args) {
        int n = 5;
        System.out.println("递归方法："+recursion(n));

        System.out.println("forMethod:"+forMethod(n));
    }

    /**
     * 题：一只青蛙一次可以跳上1级台阶，也可以跳上2个台阶。求该青蛙跳上n 级台阶一共有几种跳法？
     * 分析：
     *      台阶数 1 跳法 1  （1）
     *      台阶数 2 跳法 2  （1，1）（2）
     *      台阶数 3 跳法 3 （1，1，1）（1，2）（2，1）
     *      台阶数 4 跳法 5  （1，1，1，1）（2，2）（2，1，1）（1，2，1）（1，1，2）
     */

    /**
     *
     * @param n 台阶数
     * @return 递归解法 的缺点，会有很多重复节点的计算，而且随着n数字越大重复节点越多，浪费的空间也就越多，这样数字越大效率也就越低
     */
    public static int recursion(int n) {
        if (n <= 2) {
            return n;
        }

        return recursion(n-1)+recursion(n-2);
    }

    /**
     * 时间复杂度为 O(n) 的计算方式
     * @param n
     * @return
     */
    public static int forMethod(int n) {
        if (n <=2) {
            return n;
        }
        int x = 1;
        int y = 2;
        int t = 0;
        for (int i=3;i<=n;i++) {
            t = x +y;
            x = y;
            y = t;
        }

        return t;
    }

}
