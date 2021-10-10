package com.cczyWyc.task.task_03.gateway.inbound;

import com.cczyWyc.task.task_03.gateway.filter.HttpRequestFilter;
import com.cczyWyc.task.task_03.gateway.filter.HttpRequestFilterImpl;
import com.cczyWyc.task.task_03.gateway.outbound.httpclient.HttpClientHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import java.util.List;


/**
 * netty http handler
 *
 * @author wangyc
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {
    /** back-up server */
    //private String backupServer;
    private List<String> backupServers;
    /** httpclient handler */
    private HttpClientHandler handler;
    /** http request filter */
    private HttpRequestFilter filter = new HttpRequestFilterImpl();

    public HttpHandler(List<String> proxyServers) {
        this.backupServers = proxyServers;
        this.handler = new HttpClientHandler(this.backupServers);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            handler.handle(fullHttpRequest, ctx, filter);
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
