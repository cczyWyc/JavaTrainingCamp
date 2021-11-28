package com.cczyWyc.rpcfx.rpcfx_core.netty.client;

import com.cczyWyc.rpcfx.rpcfx_core.netty.common.RpcDecoder;
import com.cczyWyc.rpcfx.rpcfx_core.netty.common.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * rpc client initializer
 *
 * @author wangyc
 */
public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("Message Encoder", new RpcEncoder());
        pipeline.addLast("Message Decoder", new RpcDecoder());
        pipeline.addLast("clientHandler", new RpcClientSyncHandler());
    }
}
