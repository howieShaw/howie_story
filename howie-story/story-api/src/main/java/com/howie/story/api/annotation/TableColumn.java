package com.howie.story.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 4:28 PM 2018/10/24
 * @Modified by:xiaohaoyun
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableColumn {
    /**
     *
     * @return key
     */
    String columnKey() default "";

    /**
     *
     * @return 列名
     */
    String columnName() default "";

    /**
     *
     * @return 字段是否显示
     */
    boolean show() default true;

    /**
     * @return 是否需要计算总记
     */
    boolean needTotal() default false;

}
