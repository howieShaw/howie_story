package com.howie.story.biz.response;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午3:54 2018/7/31
 * @Modified by:xiaohaoyun
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        FResponse fResponse = test.getResponse();
        System.out.println(fResponse.getCode());
        System.out.println(fResponse.getMsg());
    }

    public FResponse getResponse () {
        SResponse sResponse = new SResponse();
        sResponse.setCode(200);
        sResponse.setMessage("sdfs");
        return sResponse;
    }
}
