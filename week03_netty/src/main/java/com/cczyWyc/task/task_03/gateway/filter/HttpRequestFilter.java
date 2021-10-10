package com.cczyWyc.task.task_03.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * http request filter
 *
 * @author wangyc
 */
public interface HttpRequestFilter {
    void filter(FullHttpRequest httpRequest, ChannelHandlerContext ctx);
}
