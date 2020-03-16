package com.howie.story.biz.designPattern.proxy;

public class BeforeAdvice implements Advice {

    @Override
    public void exec() {
        System.out.println("before 切面调用");
    }
}
