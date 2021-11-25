package com.cczyWyc.rpcfx_core.api;

import lombok.Data;

/**
 * rpc fx request
 *
 * @author wangyc
 */
@Data
public class RpcRequest {

    /** interface name */
    private String serviceClass;

    /** method name */
    private String method;

    /** params */
    private Object[] args;
}
