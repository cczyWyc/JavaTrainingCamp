package com.cczyWyc.rpcfx_api.model;

/**
 * @author wangyc
 */
public class Error {
    /** error status */
    private Integer status;
    /** error message */
    private String message;

    public Error(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
