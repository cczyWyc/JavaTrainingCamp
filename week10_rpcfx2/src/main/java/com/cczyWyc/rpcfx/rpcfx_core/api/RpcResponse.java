package com.cczyWyc.rpcfx.rpcfx_core.api;

/**
 * rpc fx response
 *
 * @author wangyc
 */
public class RpcResponse {

    /** response result */
    private Object result;

    /** function is succeed */
    private Boolean status;

    /** exception */
    private Exception exception;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "result=" + result +
                ", status=" + status +
                ", exception=" + exception +
                '}';
    }
}
