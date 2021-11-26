package com.cczyWyc.rpcfx_core.proxy;

/**
 * rpc client interface
 *
 * @author wangyc
 */
public interface RpcClient {
    /**
     * create client proxy
     *
     * @param serviceClass service class
     * @param url service url
     * @param <T> T
     * @return proxy class
     */
    <T> T creat(final Class<T> serviceClass, final String url);
}