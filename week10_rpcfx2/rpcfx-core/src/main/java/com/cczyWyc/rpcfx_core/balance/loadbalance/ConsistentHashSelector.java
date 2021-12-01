package com.cczyWyc.rpcfx_core.balance.loadbalance;

import com.cczyWyc.rpcfx_core.api.ProviderInfo;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.TreeMap;

/**
 * consistent hash selector
 *
 * @author wangyc
 */
public class ConsistentHashSelector {
    private final TreeMap<Long, String> virtualInvokers;
    private final int identityHashCode;

    public ConsistentHashSelector(List<ProviderInfo> providerInfos, int providersHashCode) {
        this.virtualInvokers = new TreeMap<>();
        this.identityHashCode = providersHashCode;
        for (ProviderInfo providerInfo : providerInfos) {
            String address = providerInfo.getUrl();
            int replicaNumber = 1024;
            for (int i = 0; i < replicaNumber / 4; i++) {
                byte[] digest = (address + i).getBytes(StandardCharsets.UTF_8);
                for (int h = 0; h < 4; h++) {
                    long m = hash(digest, h);
                    virtualInvokers.put(m, providerInfo.getUrl());
                }
            }
        }
    }

    public int getIdentityHashCode() {
        return identityHashCode;
    }

    String select(String key) {
        byte[] digest = key.getBytes();
        return virtualInvokers.ceilingEntry(hash(digest, 0)).getValue();
    }

    /**
     * hash
     *
     * @param digest digest
     * @param number number
     * @return long lb
     */
    private long hash(byte[] digest, int number) {
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;
    }
}
