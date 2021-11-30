package com.cczyWyc.rpcfx_core.exception;

/**
 * custom exception
 *
 * @author wangyc
 */
public class CustomException extends RuntimeException {

    /** exception message */
    private String message;

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
