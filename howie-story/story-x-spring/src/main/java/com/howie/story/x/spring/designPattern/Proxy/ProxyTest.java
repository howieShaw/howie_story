package com.howie.story.x.spring.designPattern.Proxy;

import com.howie.story.x.spring.designPattern.Proxy.dynamicProxy.DynamicProxy;
import com.howie.story.x.spring.designPattern.Proxy.staticProxy.StaticProxy;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
//        Subject subject = new StaticProxy(new RealSubject());
//        subject.print("现在是静态代理测试");
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",true);
        Subject dySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),new Class[]{Subject.class}
        ,new DynamicProxy(new RealSubject()));
        dySubject.print("动态测试");
    }
}
