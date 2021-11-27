package com.cczyWyc.rpcfx_core.proxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author wangyc
 */
@Slf4j
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
        log.info("client cglib proxy instance create and return");
        return (T) enhancer.create();
    }
}
