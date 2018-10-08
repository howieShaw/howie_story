package com.howie.story.api.constants;

import com.howie.story.api.annotation.Display;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午2:06 2018/9/27
 * @Modified by:xiaohaoyun
 */
public class CommonCode {

    public static void main(String[] args) {
        try {
            init(BizOpenStatus.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void init(Class<? extends CommonCode> clazz) throws IllegalAccessException {

        for (Field field : clazz.getFields()) {
            System.out.println("~isStatic : "+ Modifier.isStatic(field.getModifiers()));
            System.out.println("~~isPublic : "+Modifier.isPublic(field.getModifiers()));
            System.out.println("~~isAnnotation : "+field.isAnnotationPresent(Display.class));
            if (field.isAnnotationPresent(Display.class)) {
                Display annotation = field.getAnnotation(Display.class);
            }
        }

    }
}
