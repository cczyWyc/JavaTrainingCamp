package com.cczyWyc.task.task_01.gateway.inbound;

import com.cczyWyc.task.task_01.gateway.outbound.httpclient.HttpClientHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;


/**
 * netty http handler
 *
 * @author wangyc
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {
    /** back-up server */
    private String backupServer;
    /** httpclient handler */
    private HttpClientHandler handler;

    public HttpHandler(String proxyServer) {
        this.backupServer = proxyServer;
        this.handler = new HttpClientHandler(this.backupServer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            handler.handle(fullHttpRequest, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
