package com.demo.su.core.config.handler;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException{
    private ErrorCode errorCode;

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BizException(String message) {
        super(message);
        this.errorCode = BizErrorCode.UNDEFINED_ERROR;
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getStatusCode(){
        return Integer.parseInt(errorCode.getCode().substring(0,3));
    }

}
