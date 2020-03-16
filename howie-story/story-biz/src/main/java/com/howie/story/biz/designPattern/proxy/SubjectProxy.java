package com.howie.story.biz.designPattern.proxy;

public class SubjectProxy implements Subject {
    private Subject realSubject;

    public SubjectProxy (Subject subject) {
        this.realSubject = subject;
    }
    @Override
    public void request() {
        before();
        realSubject.request();
        after();
    }

    public void before () {
        System.out.println("proxy before````");
    }

    public void after () {
        System.out.println("proxy after````");
    }
}
