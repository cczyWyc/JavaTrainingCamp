package com.cczyWyc.rpcfx_core.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.InvocationTargetException;

/**
 * @author wangyc
 */
public class RpcByteBuddy extends RpcProxy implements RpcClient {
    @Override
    public <T> T creat(Class<T> serviceClass, String url) {
        if (!isExist(serviceClass.getName())) {
            try {
                addProxy(serviceClass.getName(), newProxy(serviceClass, url));
            } catch (NoSuchMethodException | InvocationTargetException |
                    InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (T) getProxy(serviceClass.getName());
    }

    /**
     * use byte buddy create new proxy
     *
     * @param serviceClass service name
     * @param url request url
     * @param <T> T
     * @return proxy
     */
    private <T> T newProxy(Class<T> serviceClass, String url) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        return (T) new ByteBuddy().subclass(Object.class)
                .implement(serviceClass)
                .intercept(InvocationHandlerAdapter.of(new RpcInvocationHandler(serviceClass, url)))
                .make()
                .load(RpcByteBuddy.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }
}
