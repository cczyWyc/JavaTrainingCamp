package com.cczyWyc.task.task_03.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * http request filter implement
 *
 * @author wangyc
 */
public class HttpRequestFilterImpl implements HttpRequestFilter{
    @Override
    public void filter(FullHttpRequest httpRequest, ChannelHandlerContext ctx) {
        httpRequest.headers().set("cczyWyc", "api");
    }
}
