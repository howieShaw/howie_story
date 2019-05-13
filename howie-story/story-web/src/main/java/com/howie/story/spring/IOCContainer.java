package com.howie.story.spring;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IOCContainer {

    private Map<String,Object> beans = new ConcurrentHashMap<>();

    public Object getBean(String beanId) {

        return beans.get(beanId);
    }

    public void setBean(String beanId,Class<?> clas,String... paramBeanId) {
        Object[] paramObjs = new Object[paramBeanId.length];

        //1 、组装构造方法所需要的参数
        for (int i=0,len = paramBeanId.length;i < len ; i++) {
            paramObjs[i] = beans.get(paramBeanId[i]);
        }

        Object bean = null;
        try {
            for (Constructor<?> constructor : clas.getConstructors()) {

                    bean = constructor.newInstance(paramObjs);

            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if (bean == null) {
            throw new RuntimeException("装配bean失败");
        }

        beans.put(beanId,bean);
    }
}
