package com.howie.story.x.spring.designPattern.Proxy;

public class RealSubject implements Subject {


    public void request(String request) {
        System.out.println("real request : "+request);
    }


    public void print(String print) {
        System.out.println("print :"+print);
    }


}
