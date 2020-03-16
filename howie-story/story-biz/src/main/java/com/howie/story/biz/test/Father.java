package com.howie.story.biz.test;

public class Father {
    public static int fa = 1;

    static {
        System.out.println("father static 代码块");
    }

    {
        System.out.println("father 非静态代码块");
    }

    public Father () {
        System.out.println("father 无参构造方法");
    }

    public Father (String name) {
        System.out.println("father 有参构造方法name : "+name);
    }
}
