package com.howie.story.x.spring.designPattern.Proxy.staticProxy;

import com.howie.story.x.spring.designPattern.Proxy.RealSubject;
import com.howie.story.x.spring.designPattern.Proxy.Subject;

public class StaticProxy implements Subject {

    private RealSubject realSubject;

    public StaticProxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }
    @Override
    public void request(String request) {
        System.out.println("static proxy before");

        try {
            realSubject.request(request);
        } catch (Exception e) {
            System.out.println("static proxy exception");
            e.printStackTrace();
        } finally {
            System.out.println("static proxy after");
        }

    }

    @Override
    public void print(String print) {
        System.out.println("``` 静态print start");
        this.realSubject.print(print);
        System.out.println("``` 静态print end");
    }
}
