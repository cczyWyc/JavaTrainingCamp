package com.cczyWyc.rpcfx_core.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * zk client
 *
 * @author wangyc
 */
public class ZookeeperClient {
    /** register root path */
    static final String REGISTER_ROOT_PATH = "rpc";

    /** client */
    protected CuratorFramework client;

    ZookeeperClient() {
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .namespace(REGISTER_ROOT_PATH)
                .retryPolicy(retry)
                .build();
        this.client.start();

        System.out.println("zookeeper service register init");
    }
}
