package com.cczyWyc.rpcfx_core.filter.server;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * black list filter
 *
 * @author wangyc
 */
public class BlackListFilter {

    /** black list set */
    private static ConcurrentSkipListSet blackList = new ConcurrentSkipListSet();

    /**
     * add black list
     *
     * @param address address
     */
    public static void addBlackAddress(String address) {
        blackList.add(address);
    }

    /**
     * check ip is in blackList
     *
     * @param address address
     * @return true/false
     */
    public static boolean checkAddress(String address) {
        return blackList.contains(address);
    }
}
