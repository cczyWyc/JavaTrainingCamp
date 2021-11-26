package com.cczyWyc.rpcfx_core.proxy;

import com.cczyWyc.rpcfx_core.api.RpcRequest;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * client invocation handler
 *
 * @author wangyc
 */

@Slf4j
public class RpcInvocationHandler implements InvocationHandler, MethodInterceptor {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return null;
    }

    /**
     * client proxy send request to server, get result from server, serialize the result into an object
     *
     * @param service service name
     * @param method service method
     * @param params method params
     * @param url server host
     * @return result object
     */
    private Object process(Class<?> service, Method method, Object[] params, String url) {
        log.info("Client proxy instance method invoke");

        //build rpc request
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceClass(service.getName());
        rpcRequest.setMethod(method.getName());
        rpcRequest.setArgs(params);


    }
}
