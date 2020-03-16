package com.howie.story.biz.designPattern.proxy;

import java.lang.reflect.Proxy;

public class DynamicProxy<T>  {

    public static <T> T newProxyInstance(ClassLoader classLoader,Class<?>[] interfaces,MyInvocationHandler handler) {

        new BeforeAdvice().exec();
        T result = (T)Proxy.newProxyInstance(classLoader,interfaces,handler);

        return result;
    }

}
