package com.cczyWyc.rpcfx.rpcfx_core.netty.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cczyWyc.rpcfx.rpcfx_core.api.RpcRequest;
import com.cczyWyc.rpcfx.rpcfx_core.api.RpcResponse;
import com.cczyWyc.rpcfx.rpcfx_core.netty.common.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * rpc server handle. use netty
 *
 * @author wangyc
 */
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcProtocol> {
    /** spring Application */
    private ApplicationContext applicationContext;

    RpcServerHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol msg) throws Exception {
        System.out.println("Rpc netty server receive message");
        System.out.println("message length:" + msg.getLength());
        System.out.println("message content:" + new String(msg.getContent(), CharsetUtil.UTF_8));

        //get rpc request from rpcProtocol, deserialize into rpc request object
        RpcRequest rpcRequest = JSON.parseObject(new String(msg.getContent(), CharsetUtil.UTF_8), RpcRequest.class);
        System.out.println("Netty server serialize:" + rpcRequest.toString());

        //get bean, invoke
        RpcResponse rpcResponse = invoke(rpcRequest);

        //return result to netty client
        RpcProtocol message = new RpcProtocol();
        String rpcResponseJson = JSON.toJSONString(rpcResponse);
        message.setLength(rpcResponseJson.getBytes(StandardCharsets.UTF_8).length);
        message.setContent(rpcResponseJson.getBytes(StandardCharsets.UTF_8));

        ctx.writeAndFlush(message);
        System.out.println("rpc netty client send message to client finish");
    }

    /**
     * get interface bean, reflect invoke function, get response
     *
     * @param rpcRequest rpc request
     * @return rpc response
     */
    private RpcResponse invoke(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        String serviceClass = rpcRequest.getServiceClass();

        Object service = applicationContext.getBean(serviceClass);
        try {
            Method method = resolveMethodFromClass(service.getClass(), rpcRequest.getMethod());
            Object result = method.invoke(service, rpcRequest.getArgs());
            System.out.println("Server method invoke result:" + result.toString());
            rpcResponse.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            rpcResponse.setResult(true);
            System.out.println("Server Response serialize to string return");
            return rpcResponse;
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
            rpcResponse.setException(e);
            rpcResponse.setStatus(false);
            return rpcResponse;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }
}
