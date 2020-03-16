package com.howie.story.biz.designPattern.proxy;

public class ProxyMain {

    public static void main(String[] args) {
        /**
         * 静态代理调用
         */
//        SubjectProxy proxy = new SubjectProxy(new RealSubject());
//        proxy.request();
        /**
         * 动态代理调用
         */
        Subject subject = new RealSubject();
        MyInvocationHandler handler = new MyInvocationHandler(subject);

        Subject proxy = DynamicProxy.newProxyInstance(subject.getClass().getClassLoader(),subject.getClass().getInterfaces(),handler);

        proxy.request();
    }
}
