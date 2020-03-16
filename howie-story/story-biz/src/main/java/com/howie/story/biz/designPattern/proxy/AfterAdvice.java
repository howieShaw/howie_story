package com.howie.story.biz.designPattern.proxy;

public class AfterAdvice implements Advice {
    @Override
    public void exec() {
        System.out.println("after advice 日志答应");
    }
}
