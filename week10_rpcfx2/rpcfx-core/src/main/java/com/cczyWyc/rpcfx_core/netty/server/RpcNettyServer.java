package com.cczyWyc.rpcfx_core.netty.server;

import com.cczyWyc.rpcfx_core.netty.common.RpcDecoder;
import com.cczyWyc.rpcfx_core.netty.common.RpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wangyc
 */
@Component
public class RpcNettyServer {
    /** spring application */
    private ApplicationContext context;
    /** boss group */
    private EventLoopGroup bossGroup;
    /** worker group */
    private EventLoopGroup workerGroup;

    public RpcNettyServer(ApplicationContext context) {
        this.context = context;
    }

    /**
     * netty server run
     */
    public void run() throws InterruptedException {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("Black List", new BlackListFilterHandler());
                        pipeline.addLast("Message Encoder", new RpcEncoder());
                        pipeline.addLast("Message Decoder", new RpcDecoder());
                        pipeline.addLast(new RpcServerHandler(context));
                    }
                });
        int port = 8080;
        Channel channel = serverBootstrap.bind(port).sync().channel();
        System.out.println("Rpc netty server is start, listing port is:" + port);
        channel.closeFuture().sync();
    }

    /**
     * close event loop group
     */
    public void close() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
