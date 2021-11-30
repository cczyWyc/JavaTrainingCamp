package com.cczywyc.rpcfxserver;

import com.cczyWyc.rpcfx_core.netty.server.RpcNettyServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpcfxServerApplication implements ApplicationRunner {


    private final RpcNettyServer rpcNettyServer;

    public RpcfxServerApplication(RpcNettyServer rpcNettyServer) {
        this.rpcNettyServer = rpcNettyServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(RpcfxServerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            rpcNettyServer.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rpcNettyServer.close();
        }
    }
}
