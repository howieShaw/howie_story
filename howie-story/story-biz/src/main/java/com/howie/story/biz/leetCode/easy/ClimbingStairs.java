package com.howie.story.biz.leetCode.easy;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午10:20 2018/8/4
 * @Modified by:xiaohaoyun
 */
public class ClimbingStairs {
    /**
     * You are climbing a stair case. It takes n steps to reach to the top.

     Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     需要爬一个n阶楼梯来到达楼梯顶部，每次可以爬1阶也可以爬2阶。求共有多少种爬法？
     */

    public static void main(String[] args) {
        System.out.println(climbingWay(5));
    }

    public static int climbingWay (int n) {

        if (n <2) {
            return n;
        }
        int stair1 = 1;
        int stair2 =2;
        int way = 0;
        for (int i =3; i <=n; i++) {
            way = stair1 + stair2;
            stair1 = stair2;
            stair2 = way;
        }
        return way;
    }
}
