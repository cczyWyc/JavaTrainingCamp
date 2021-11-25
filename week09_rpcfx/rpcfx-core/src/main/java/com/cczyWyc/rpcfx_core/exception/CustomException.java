package com.cczyWyc.rpcfx_core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * custom exception
 *
 * @author wangyc
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
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
}
