package com.howie.story.biz.zPracticeUtil.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:xiaohaoyun
 * @Description： 运行时常量池导致的内存溢出
 * @Date：created in 下午3:02 2018/7/8
 * @Modified by:xiaohaoyun
 */
public class ConstantPoolOOM {

    /**
     * VM Args ：-XX:PermSize=10M  -XX:MaxPermSize-10M
     * 该方法只在JDK 1.6 及之前版本才有效，而使用JDK1.7 while 循环将一直进行下去
     *
     */
    public void  runtimeConstantOOM () {
        List<String> list = new ArrayList<String>();
        int i =0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    /**
     * 这段代码在1.6 会的都两个false ，而在1.7 中运行，会得到一个true 一个false
     */
    public void runtimeConstantPollString() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);
    }

    public static void main(String[] args) {
        ConstantPoolOOM poolOOM = new ConstantPoolOOM();
        poolOOM.runtimeConstantPollString();
    }
}
