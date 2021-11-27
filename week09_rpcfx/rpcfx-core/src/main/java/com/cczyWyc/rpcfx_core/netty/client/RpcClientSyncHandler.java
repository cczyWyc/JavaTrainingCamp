package com.cczyWyc.rpcfx_core.netty.client;

import com.alibaba.fastjson.JSON;
import com.cczyWyc.rpcfx_core.api.RpcResponse;
import com.cczyWyc.rpcfx_core.netty.common.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * rpc client handler
 *
 * @author wangyc
 */
@Slf4j
public class RpcClientSyncHandler extends SimpleChannelInboundHandler<RpcProtocol> {

    /** concurrent util */
    private CountDownLatch latch;
    /** response */
    private RpcResponse response;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol msg) throws Exception {
        log.info("Netty rpc client receive message");
        log.info("Message length:" + msg.getLength());
        log.info("Message content:" + new String(msg.getContent(), CharsetUtil.UTF_8));

        //rpcResponse str---->rpcResponse object
        RpcResponse rpcResponse = JSON.parseObject(new String(msg.getContent(), CharsetUtil.UTF_8), RpcResponse.class);
        log.info("Netty rpc client serializer:" + rpcResponse.toString());
        //concurrent wait-notice to get result
        response = rpcResponse;
        latch.countDown();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("error:" + cause.getMessage());
        ctx.close();
    }

    /**
     * init lock
     *
     * @param latch CountDownLatch
     */
    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     *
     *
     * @return
     * @throws InterruptedException
     */
    RpcResponse getResponse() throws InterruptedException {
        latch.await();
        return response;
    }
}
