package com.cczyWyc.rpcfx_core.proxy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * rpc proxy
 *
 * @author wangyc
 */
public class RpcProxy {
    /** proxy cache */
    private ConcurrentHashMap<String, Object> proxyCache = new ConcurrentHashMap<>();

    Object getProxy(String className) {
        return proxyCache.get(proxyCache);
    }

    Boolean isExist(String className) {
        return proxyCache.containsKey(className);
    }

    void addProxy(String className, Object proxy) {
        proxyCache.put(className, proxy);
    }
}
