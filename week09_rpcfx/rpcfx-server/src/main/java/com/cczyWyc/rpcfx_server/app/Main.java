package com.cczyWyc.rpcfx_server.app;

import com.cczyWyc.rpcfx_core.netty.server.RpcNettyServer;

/**
 * main
 *
 * @author wangyc
 */
public class Main {

    private final RpcNettyServer rpcNettyServer;

    public Main(RpcNettyServer rpcNettyServer) {
        this.rpcNettyServer = rpcNettyServer;
    }

    public static void main(String[] args) {
        //new Main().run();
    }

    public void run() {
        try {
            rpcNettyServer.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rpcNettyServer.close();
        }
    }
}
