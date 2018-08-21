package com.howie.story.biz.util;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午8:21 2018/5/24
 * @Modified by:xiaohaoyun
 */
public class EntityUtils {

    public static void main(String[] args) {
        EntityUtils utils = new EntityUtils();
        System.out.println(utils.inx());
    }

    private int inx() {
        int x;
        try {
            x = 2;
            return x;
        } catch (Exception e) {
            x = 3;
            return x;
        } finally {
            x =4;
        }
    }
}
