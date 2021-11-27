package com.cczyWyc.rpcfx_core.api;

import java.util.Arrays;

/**
 * rpc fx request
 *
 * @author wangyc
 */
public class RpcRequest {

    /** interface name */
    private String serviceClass;

    /** method name */
    private String method;

    /** params */
    private Object[] args;

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "serviceClass='" + serviceClass + '\'' +
                ", method='" + method + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
