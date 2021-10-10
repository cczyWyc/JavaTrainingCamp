package com.cczyWyc.task.task_03.gateway;

import com.cczyWyc.task.task_03.gateway.inbound.NettyHttpServer;

/**
 * gateway netty server main
 *
 * @author wangyc
 */
public class GatewayServerMain {
    public static void main(String[] args) {
        //gateway http server port
        String proxyPort = System.getProperty("proxyPort", "8808");

        //httpserver01 is back-up server, listened 8801
        String backupServer = System.getProperty("proxyServer", "http://localhost:8801");

        int port = Integer.parseInt(proxyPort);
        System.out.println("Gateway service is starting......");
        NettyHttpServer server = new NettyHttpServer(port, backupServer);
        System.out.println("Gateway service is started, listened port is " + port);
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
