package com.cczyWyc.rpcfx_core.api;

import lombok.Data;

/**
 * rpc fx response
 *
 * @author wangyc
 */
@Data
public class RpcResponse {

    /** response result */
    private Object result;

    /** function is succeed */
    private Boolean status;

    /** exception */
    private Exception exception;
}
