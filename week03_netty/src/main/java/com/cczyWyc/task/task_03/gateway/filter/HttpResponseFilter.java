package com.cczyWyc.task.task_03.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * http response filter
 *
 * @author wangyc
 */
public interface HttpResponseFilter {
    void filter(FullHttpResponse httpResponse);
}
