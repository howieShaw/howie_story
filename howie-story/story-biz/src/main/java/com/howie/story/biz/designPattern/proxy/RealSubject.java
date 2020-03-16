package com.howie.story.biz.designPattern.proxy;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("realSubject request 被调用");
    }
}
