package com.howie.story.api.bean.response;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午6:29 2018/9/7
 * @Modified by:xiaohaoyun
 */
public class BaseResponse<T> {
    private Integer code;
    private String msg;
    private T data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
