package com.cczyWyc.rpcfx_core.netty.server;

import com.alibaba.fastjson.JSON;
import com.cczyWyc.rpcfx_core.api.RpcResponse;
import com.cczyWyc.rpcfx_core.filter.server.BlackListFilter;
import com.cczyWyc.rpcfx_core.netty.common.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * netty server black list filter handler
 *
 * @author wangyc
 */
public class BlackListFilterHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InetSocketAddress socket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = socket.getAddress().getHostAddress();
        System.out.println("client ip is:" + clientIp);

        if (BlackListFilter.checkAddress(clientIp)) {
            RpcResponse response = new RpcResponse();
            response.setStatus(false);
            response.setException(new Exception("black list"));

            RpcProtocol message = new RpcProtocol();
            String requestJson = JSON.toJSONString(response);
            message.setLength(requestJson.getBytes(StandardCharsets.UTF_8).length);
            message.setContent(requestJson.getBytes(StandardCharsets.UTF_8));

            ctx.channel().writeAndFlush(message).sync();
        }
        ctx.fireChannelRead(msg);
    }
}
