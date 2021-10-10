package com.cczyWyc.task.task_03.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * http response filter implement
 *
 * @author wangyc
 */
public class HttpResponseFilterImpl implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse httpResponse) {
        httpResponse.headers().set("geekTime", "hello netty");
    }
}
