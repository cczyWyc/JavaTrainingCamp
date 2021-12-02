package com.cczyWyc.rpcfx_core.filter.client;

/**
 * @author wangyc
 */
public class Retry {
    private static int retryLimit = 0;

    public static int getRetryLimit() {
        return retryLimit;
    }

    public static void setRetryLimit(int retryLimit) {
        Retry.retryLimit = retryLimit;
    }
}
