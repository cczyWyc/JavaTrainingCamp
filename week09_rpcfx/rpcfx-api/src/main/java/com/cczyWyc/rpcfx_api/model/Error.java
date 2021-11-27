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
}
