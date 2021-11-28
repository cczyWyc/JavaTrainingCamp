package com.cczyWyc.rpcfx.rpcfx_core.proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author wangyc
 */
public class RpcClientCglib extends RpcProxy implements RpcClient {
    @Override
    public <T> T creat(Class<T> serviceClass, String url) {
        if (!isExist(serviceClass.getName())) {
            //not exist, create it
            addProxy(serviceClass.getName(), newProxy(serviceClass, url));
        }
        return (T) getProxy(serviceClass.getName());
    }

    /**
     * use cglib create new proxy
     *
     * @param serviceName service name
     * @param url request url
     * @param <T> T
     * @return proxy
     */
    private <T> T newProxy(Class<T> serviceName, String url) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new RpcInvocationHandler(serviceName, url));
        enhancer.setSuperclass(serviceName);
        System.out.println("client cglib proxy instance create and return");
        return (T) enhancer.create();
    }
}
