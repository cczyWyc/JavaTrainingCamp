package com.cczyWyc.rpcfx_core.proxy;

import com.cczyWyc.rpcfx_core.balance.loadbalance.WeightBalance;
import com.google.common.base.Joiner;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * rpc client
 *
 * @author wangyc
 */
public class RpcClient {

    /** rpc client name */
    private static String balanceAlgorithmName = WeightBalance.NAME;
    /** proxy cache */
    private ConcurrentHashMap<String, Object> proxyCache = new ConcurrentHashMap<>();

    public static void setBalanceAlgorithmName(String balanceAlgorithm) {
        balanceAlgorithmName = balanceAlgorithm;
    }

    public static String getBalanceAlgorithmName() {
        return balanceAlgorithmName;
    }

    private Object getProxy(String className) {
        return proxyCache.get(className);
    }

    private Boolean isExit(String className) {
        return proxyCache.containsKey(className);
    }

    private void add(String className, Object proxy) {
        proxyCache.put(className, proxy);
    }

    public <T> T create(Class<T> serviceClass) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        String invoker = serviceClass.getName();
        if (!isExit(invoker)) {
            add(invoker, newProxy(serviceClass));
        }
        return (T) getProxy(invoker);
    }

    public <T> T create(Class<T> serviceClass, String group, String version) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        String invoker = Joiner.on(":").join(serviceClass.getName(), group, version);
        if (!isExit(invoker)) {
            add(invoker, newProxy(serviceClass, group, version));
        }
        return (T) getProxy(invoker);
    }

    public <T> T create(Class<T> serviceClass, String group, String version, List<String> tags)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String invoker = Joiner.on(":").join(serviceClass.getName(), group, version, tags.toString());
        if (!isExit(invoker)) {
            add(invoker, newProxy(serviceClass, group, version, tags));
        }
        return (T) getProxy(invoker);
    }

    private <T> T newProxy(Class<T> serviceClass, String group, String version) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        return (T) new ByteBuddy().subclass(Object.class)
                .implement(serviceClass)
                .intercept(InvocationHandlerAdapter.of(new RpcInvocationHandler(serviceClass, group, version)))
                .make()
                .load(RpcClient.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

    private <T> T newProxy(Class<T> serviceClass, String group, String version, List<String> tags)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (T) new ByteBuddy().subclass(Object.class)
                .implement(serviceClass)
                .intercept(InvocationHandlerAdapter.of(new RpcInvocationHandler(serviceClass, group, version, tags)))
                .make()
                .load(RpcClient.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

    private <T> T newProxy(Class<T> serviceClass) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        return (T) new ByteBuddy().subclass(Object.class)
                .implement(serviceClass)
                .intercept(InvocationHandlerAdapter.of(new RpcInvocationHandler(serviceClass)))
                .make()
                .load(RpcClient.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }
}
