package com.cczyWyc.rpcfx_core.proxy;

import java.lang.reflect.Proxy;

/**
 * use jdk create client proxy
 *
 * @author wangyc
 */
public class RpcClientJdk extends RpcProxy implements RpcClient {
    @Override
    public <T> T creat(Class<T> serviceClass, String url) {
        if (!isExist(serviceClass.getName())) {
            addProxy(serviceClass.getName(), newProxy(serviceClass, url));
        }
        return (T) getProxy(serviceClass.getName());
    }

    /**
     * use jdk create new proxy
     *
     * @param serviceClass service name
     * @param url request url
     * @param <T> T
     * @return proxy
     */
    private <T> T newProxy(Class<T> serviceClass, String url) {
        ClassLoader classLoader = RpcClient.class.getClassLoader();
        Class[] classes = new Class[]{serviceClass};
        return (T) Proxy.newProxyInstance(classLoader, classes, new RpcInvocationHandler(serviceClass, url));
    }
}
