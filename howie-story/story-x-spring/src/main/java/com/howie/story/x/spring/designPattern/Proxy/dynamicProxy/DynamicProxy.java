package com.howie.story.x.spring.designPattern.Proxy.dynamicProxy;

import com.howie.story.x.spring.designPattern.Proxy.RealSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    private RealSubject realSubject;

    public DynamicProxy (RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("········动态代理 before········");
        Object object = null;
        try {
            object = method.invoke(realSubject,args);
        } catch (Exception e) {
            System.out.println("```````` 动态代理 exception````````");
            e.printStackTrace();
        } finally {
            System.out.println("`````` 动态代理 after");
        }
        return object;
    }
}
