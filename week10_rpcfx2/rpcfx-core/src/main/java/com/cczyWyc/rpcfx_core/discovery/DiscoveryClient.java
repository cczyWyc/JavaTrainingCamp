package com.cczyWyc.rpcfx_core.discovery;

/**
 * discovery client
 * get provider list
 * listen provider update
 *
 * @author wangyc
 */
public class DiscoveryClient extends ZookeeperClient {

    /**
     * lazy singleton
     */
    private enum EnumSingleton {
        INSTANCE;
        private DiscoveryClient instance;

        EnumSingleton() {
            instance = new DiscoveryClient();
        }

        public DiscoveryClient getSingleton() {
            return instance;
        }
    }

    public static DiscoveryClient getInstance() {
        return EnumSingleton.INSTANCE.getSingleton();
    }
}
