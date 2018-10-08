package com.howie.story.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 上午11:52 2018/9/27
 * @Modified by:xiaohaoyun
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Display {
    String value() default "";
}
