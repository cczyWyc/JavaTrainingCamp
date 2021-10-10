package com.cczyWyc.task.task_03.gateway.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * netty http initializer
 *
 * @author wangyc
 */
public class HttpInitializer extends ChannelInitializer<SocketChannel> {
    /** back-up server */
    private String backupServer;

    public HttpInitializer(String backupServer) {
        this.backupServer = backupServer;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new HttpHandler(backupServer));
    }
}
