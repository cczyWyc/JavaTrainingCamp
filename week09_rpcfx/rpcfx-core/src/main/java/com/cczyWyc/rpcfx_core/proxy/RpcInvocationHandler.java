package com.cczyWyc.rpcfx_core.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.cczyWyc.rpcfx_core.api.RpcRequest;
import com.cczyWyc.rpcfx_core.api.RpcResponse;
import com.cczyWyc.rpcfx_core.netty.client.RpcNettyClientSync;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

/**
 * client invocation handler
 *
 * @author wangyc
 */

@Slf4j
public class RpcInvocationHandler implements InvocationHandler, MethodInterceptor {

    /** request service class */
    private final Class<?> serviceClass;
    /** request url */
    private final String url;

    <T> RpcInvocationHandler(Class<?> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return process(serviceClass, method, args, url);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return process(serviceClass, method, args, url);
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

        //client proxy use netty send request to server, get result
        RpcResponse rpcResponse;
        try {
            rpcResponse = RpcNettyClientSync.getInstance().getResponse(rpcRequest, url);
        } catch (URISyntaxException | InterruptedException e) {
            log.warn(e.getMessage());
            return null;
        }
        log.info("client receive response object");
        assert rpcResponse != null;
        if (!rpcResponse.getStatus()) {
            log.info("client receive failed!");
            log.error(rpcResponse.getException().getMessage());
            return null;
        }

        //serialization response to object and return
        log.info("Response:" + rpcResponse.getResult());
        return JSON.parse(rpcResponse.getResult().toString());
    }
}
