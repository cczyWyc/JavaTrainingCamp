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

    /** group */
    private String group;

    /** version */
    private String version;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "serviceClass='" + serviceClass + '\'' +
                ", method='" + method + '\'' +
                ", args=" + Arrays.toString(args) +
                ", group='" + group + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
