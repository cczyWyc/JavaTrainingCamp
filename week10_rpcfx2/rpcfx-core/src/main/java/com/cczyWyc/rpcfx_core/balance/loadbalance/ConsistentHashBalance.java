package com.cczyWyc.rpcfx_core.balance.loadbalance;

import com.cczyWyc.rpcfx_core.api.ProviderInfo;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * consistent hash balance
 *
 * @author wangyc
 */
public class ConsistentHashBalance extends AbstractLoadBalance{

    /** hash name */
    public static final String NAME = "consistent_hash_balance";
    /** selectors */
    private final ConcurrentHashMap<String, ConsistentHashSelector> selectors = new ConcurrentHashMap<>();

    @Override
    public String select(List<ProviderInfo> providerInfos, String serviceName, String methodName) {
        String key = serviceName + ":" + methodName;
        int providerHashCode = providerInfos.hashCode();
        ConsistentHashSelector selector = selectors.get(key);
        if (selector == null || selector.getIdentityHashCode() != providerHashCode) {
            selectors.put(key, new ConsistentHashSelector(providerInfos, providerHashCode));
            selector = selectors.get(key);
        }
        return selector.select(key);
    }
}
