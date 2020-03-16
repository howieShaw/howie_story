package com.howie.story.biz.test;

public class Son extends Father {
    public static int so = 2;

    static {
        System.out.println("son static 方法块");
    }

    {
        System.out.println("son 非静态代码块");
    }

    public Son () {
        System.out.println("Son 无参构造方法");
    }

    public Son (String name) {
        System.out.println("Son 有参构造方法 name : "+name);
    }

    public static void main(String[] args) {
        Son son = new Son();

        System.out.println("~~~~~~~~~~~~~~~~~~");
        Son son1 = new Son("xiao");
    }
}
