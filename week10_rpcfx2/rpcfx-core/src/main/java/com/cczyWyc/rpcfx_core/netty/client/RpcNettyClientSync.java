package com.cczyWyc.rpcfx_core.netty.client;

import com.alibaba.fastjson.JSON;
import com.cczyWyc.rpcfx_core.api.RpcRequest;
import com.cczyWyc.rpcfx_core.api.RpcResponse;
import com.cczyWyc.rpcfx_core.netty.common.RpcProtocol;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * rpc client use netty. send request and get response from handler
 *
 * @author wangyc
 */
public class RpcNettyClientSync {

    /** Singleton mode. */
    private enum EnumSingleton {
        /** Lazy enumeration */
        INSTANCE;
        private RpcNettyClientSync instance;

        EnumSingleton() {
            instance = new RpcNettyClientSync();
        }

        public RpcNettyClientSync getSingleton() {
            return instance;
        }
    }

    /**
     * get instance
     *
     * @return instance
     */
    public static RpcNettyClientSync getInstance() {
        return EnumSingleton.INSTANCE.getSingleton();
    }

    public RpcNettyClientSync() {
    }

    /** channel cache */
    private ConcurrentHashMap<String, Channel> channelPool = new ConcurrentHashMap<>();
    /** client event loop group */
    private final EventLoopGroup clientGroup = new NioEventLoopGroup(new ThreadFactoryBuilder()
            .setNameFormat("client work-%d").build());

    public RpcResponse getResponse(RpcRequest rpcRequest, String url) throws URISyntaxException, InterruptedException {
        RpcProtocol request = convertNettyRequest(rpcRequest);

        URI uri = new URI(url);
        String cacheKey = uri.getHost() + ":" + uri.getPort();

        //Check if there are reused channels in the cache
        if (channelPool.containsKey(cacheKey)) {
            Channel channel = channelPool.get(cacheKey);
            if (!channel.isActive() || !channel.isWritable() || !channel.isOpen()) {
                System.out.println("Channel can't reuse");
            } else {
                try {
                    RpcClientSyncHandler handler = new RpcClientSyncHandler();
                    handler.setLatch(new CountDownLatch(1));
                    channel.pipeline().replace("clientHandler", "clientHandler", handler);
                    channel.writeAndFlush(request).sync();
                    return handler.getResponse();
                } catch (InterruptedException e) {
                    System.out.println("channel reuse send msg failed!");
                    channel.close();
                    channelPool.remove(cacheKey);
                }
                System.out.println("handler is busy, please use new channel");
            }
        }

        //cache has no channel, create new channel
        RpcClientSyncHandler handler = new RpcClientSyncHandler();
        handler.setLatch(new CountDownLatch(1));
        Channel channel = createChannel(uri.getHost(), uri.getPort());
        channel.pipeline().replace("clientHandler", "clientHandler", handler);
        channelPool.put(cacheKey, channel);

        channel.writeAndFlush(request).sync();
        return handler.getResponse();
    }

    /**
     * create channel
     *
     * @param address ip address
     * @param port port
     * @return channel
     * @throws InterruptedException ex
     */
    private Channel createChannel(String address, int port) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.AUTO_CLOSE, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioSocketChannel.class)
                .handler(new RpcClientInitializer());
        return bootstrap.connect(address, port).sync().channel();
    }

    /**
     * convert {@RPCRequest} to netty custom {@RpcProtocol}
     *
     * @param rpcRequest rpcRequest
     * @return rpcProtocol
     */
    private RpcProtocol convertNettyRequest(RpcRequest rpcRequest) {
        RpcProtocol request = new RpcProtocol();
        String requestJson = JSON.toJSONString(rpcRequest);
        request.setLength(requestJson.getBytes(StandardCharsets.UTF_8).length);
        request.setContent(requestJson.getBytes(StandardCharsets.UTF_8));
        return request;
    }

    /**
     * close group
     */
    public void closeGroup() {
        clientGroup.shutdownGracefully();
    }
}
