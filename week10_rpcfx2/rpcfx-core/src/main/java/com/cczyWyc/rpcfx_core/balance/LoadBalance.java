package com.cczyWyc.rpcfx_core.balance;

import com.cczyWyc.rpcfx_core.api.ProviderInfo;

import java.util.List;

/**
 * @author wangyc
 */
public interface LoadBalance {
    /**
     * use load balance return provider address from current list of providers
     *
     * @param providerInfos provider list
     * @param serviceName service name
     * @param methodName method name
     * @return provider host url
     */
    String select(List<ProviderInfo> providerInfos, String serviceName, String methodName);
}
