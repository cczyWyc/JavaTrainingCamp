package com.cczyWyc.task.task_01.gateway.outbound.httpclient;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * httpclient handler
 *
 * @author wangyc
 */
public class HttpClientHandler {
    /** back_up server */
    private String backupServer;
    /** executorService */
    private ExecutorService executorService;
    /** asynchronous httpclient */
    private CloseableHttpAsyncClient httpClient;

    /**
     * init http client
     *
     * @param backupServer request back-up server url
     */
    public HttpClientHandler(String backupServer) {
        this.backupServer = backupServer;
        //max thread number
        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        //Rejection strategy
        RejectedExecutionHandler handler =  new ThreadPoolExecutor.CallerRunsPolicy();
        executorService =new ThreadPoolExecutor(cores, cores, keepAliveTime, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize), new HttpClientThreadFactory("httpExecutorService"), handler);
        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();
        httpClient = HttpAsyncClients.custom()
                .setMaxConnTotal(40)
                .setDefaultIOReactorConfig(ioConfig)
                .setKeepAliveStrategy((response, context) -> 6000)
                .build();
        httpClient.start();

    }

    /**
     * http client send get request to back-up service
     *
     * @param httpRequest httpRequest
     * @param ctx ChannelHandlerContext
     * @param url httpclient request backup service url
     */
    public void getRequest(final FullHttpRequest httpRequest, final ChannelHandlerContext ctx, String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse result) {
                try {
                    handleResponse(httpRequest, ctx, result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception ex) {
                httpGet.abort();
                ex.printStackTrace();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }

    public void handle(FullHttpRequest httpRequest, ChannelHandlerContext ctx) {
        String url = backupServer + httpRequest.uri();
        executorService.submit(() -> getRequest(httpRequest, ctx, url));
    }

    /**
     * handle http response to client
     *
     * @param httpRequest httpRequest
     * @param ctx ChannelHandlerContext
     * @param result http response
     */
    private void handleResponse(FullHttpRequest httpRequest, ChannelHandlerContext ctx, HttpResponse result) throws IOException {
        FullHttpResponse httpResponse = null;
        try {
            byte[] body = EntityUtils.toByteArray(result.getEntity());
            httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            httpResponse.headers().set("Content-Type", "application/json");
            httpResponse.headers().setInt("Content-Length",
                    Integer.parseInt(result.getFirstHeader("Content-Length").getValue()));
        } catch (Exception e) {
            e.printStackTrace();
            httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (httpRequest != null) {
                if (!HttpUtil.isKeepAlive(httpRequest)) {
                    ctx.write(httpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                    ctx.write(httpResponse);
                }
            }
            ctx.flush();
        }
    }

    private void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.flush();
    }
}
