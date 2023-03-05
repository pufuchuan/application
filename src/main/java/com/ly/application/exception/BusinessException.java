package com.ly.application.exception;

public class BusinessException extends RuntimeException {

    private static int errorCode = 500;

    public BusinessException(String msg, int errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BusinessException(String msg) {
        super(msg);
    }


}
