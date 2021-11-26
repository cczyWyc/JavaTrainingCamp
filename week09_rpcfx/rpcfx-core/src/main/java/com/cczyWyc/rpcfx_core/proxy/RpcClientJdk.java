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
        return null;
    }

    private <T> T newProxy(Class<T> serviceClass, String url) {
        ClassLoader classLoader = RpcClient.class.getClassLoader();
        Class[] classes = new Class[]{serviceClass};
        return Proxy.newProxyInstance(classLoader, classes, )
    }
}
