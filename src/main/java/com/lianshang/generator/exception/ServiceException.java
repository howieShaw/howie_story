package com.lianshang.generator.exception;

/**
 * Created by walker on 16/2/4.
 */
public class ServiceException extends Exception {

    private int code;

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
