package com.cczyWyc.rpcfx_core.netty.client;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * rpc client use netty. send request and get response from handler
 *
 * @author wangyc
 */

@Slf4j
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
    private EventLoopGroup clientGroup = new NioEventLoopGroup(new ThreadFactoryBuilder().setNameFormat("client work-%d").build());

    
}
