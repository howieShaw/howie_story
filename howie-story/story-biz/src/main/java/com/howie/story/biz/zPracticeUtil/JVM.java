package com.howie.story.biz.zPracticeUtil;

import java.io.UnsupportedEncodingException;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午2:08 2018/7/8
 * @Modified by:xiaohaoyun
 */
public class JVM {

    public static void main(String[] args) {
        String str = "a一个";
        try {
            byte[] gbks = str.getBytes("GBK");
            byte[] utf = str.getBytes("utf-8");
            System.out.println("gbk 长度 : "+gbks.length);
            System.out.println("utf 长度 : "+utf.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
