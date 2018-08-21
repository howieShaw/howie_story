package com.howie.story.biz.viewImpl;

import com.howie.story.api.service.view.BaseViewService;
import com.howie.story.biz.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午5:51 2018/5/25
 * @Modified by:xiaohaoyun
 */
public class BaseViewServiceImpl implements BaseViewService {

    /**
     * 将字符串转化为数字类型
     * @param param 入参
     * @return
     */
    public Integer strToInteger(String param) {
        if (StringUtils.isBlank(param) || param.length() <= 0) {
            return null;
        }

        if (!CommonUtils.isNumber(param)) {
            System.out.println("非数字类型");
            return null;
        }
        int len = param.length();
        boolean negative = false;
        int index = 0;
        int result = 0;
        char firstChar = param.charAt(0);
        if (firstChar < '0' && firstChar == '-') {
            negative = true;
            index++;
        }

        while (index < len) {
            int digit = Character.digit(param.charAt(index++),10);
            if (digit < 0) {
                return null;
            }
            result *=10;
            result += digit;
        }

        return negative ? -result : result;
    }

    public Integer incrementNumber(int number,int count) {
        AtomicInteger atomicInteger = new AtomicInteger(number);
        int index =0;
        while (index < count) {
            atomicInteger.incrementAndGet();
        }
        return atomicInteger.get();
    }



    public static void main(String[] args) {


    }


}
