package com.howie.story.api.constants;

import com.howie.story.api.annotation.Display;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 上午11:56 2018/9/27
 * @Modified by:xiaohaoyun
 */
public class ShopStatus extends BizOpenStatus {
    @Display("关店")
    public static final int CLOSE = 12;


}
