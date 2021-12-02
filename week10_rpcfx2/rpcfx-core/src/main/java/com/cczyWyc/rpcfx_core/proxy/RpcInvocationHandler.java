package com.cczyWyc.rpcfx_core.proxy;

import com.alibaba.fastjson.JSON;
import com.cczyWyc.rpcfx_core.api.RpcRequest;
import com.cczyWyc.rpcfx_core.api.RpcResponse;
import com.cczyWyc.rpcfx_core.discovery.DiscoveryClient;
import com.cczyWyc.rpcfx_core.netty.client.RpcNettyClientSync;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyc
 */
public class RpcInvocationHandler implements InvocationHandler, MethodInterceptor {
    /** service class */
    private final Class<?> serviceClass;
    /** group */
    private final String group;
    /** version */
    private final String version;
    /** discovery client */
    private final DiscoveryClient discoveryClient = DiscoveryClient.getInstance();
    /** tags */
    private final List<String> tags = new ArrayList<>();
    /** retry time */
    private int retryTime = 0;

    <T> RpcInvocationHandler(Class<T> serviceClass) {
        this.serviceClass = serviceClass;
        this.group = "default";
        this.version = "default";

        System.out.println("Client Service Class Reflect: " + group + ":" + version);
    }

    <T> RpcInvocationHandler(Class<T> serviceClass, String group, String version) {
        this.serviceClass = serviceClass;
        this.group = group;
        this.version = version;

        System.out.println("Client Service Class Reflect: " + group + ":" + version);
    }

    <T> RpcInvocationHandler(Class<T> serviceClass, String group, String version, List<String> tags) {
        this.serviceClass = serviceClass;
        this.group = group;
        this.version = version;
        this.tags.addAll(tags);

        System.out.println("Client Service Class Reflect: " + group + ":" + version);
    }


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
     * @return result object
     */
    private Object process(Class<?> service, Method method, Object[] params) {
        System.out.println("Client proxy instance method invoke");

        //build rpc request
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceClass(service.getName());
        rpcRequest.setMethod(method.getName());
        rpcRequest.setArgs(params);
        rpcRequest.setGroup(group);
        rpcRequest.setVersion(version);

        String url = null;
        url = discoveryClient.getProviders(service.getName(), group, version, tags, method.getName());
        if (url == null) {
            System.out.println("Can not find provider");
            return null;
        }

        //client proxy use netty send request to server, get result
        RpcResponse rpcResponse;
        try {
            rpcResponse = RpcNettyClientSync.getInstance().getResponse(rpcRequest, url);
        } catch (URISyntaxException | InterruptedException e) {
            System.out.println(e.getMessage());
            return null;
        }
        System.out.println("client receive response object");
        assert rpcResponse != null;
        if (!rpcResponse.getStatus()) {
            System.out.println("client receive failed!");
            System.out.println(rpcResponse.getException().getMessage());
            return null;
        }

        //serialization response to object and return
        System.out.println("Response:" + rpcResponse.getResult());
        return JSON.parse(rpcResponse.getResult().toString());
    }
}
