package com.howie.story.biz.response;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午3:54 2018/7/31
 * @Modified by:xiaohaoyun
 */
public class SResponse extends FResponse {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
