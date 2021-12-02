package com.cczyWyc.rpcfx_core.filter.client;

import com.cczyWyc.rpcfx_core.api.ProviderInfo;

import java.util.List;

/**
 * rpx filter interface
 *
 * @author wangyc
 */
public interface RpcFilter {
    List<ProviderInfo> filter(List<ProviderInfo> providerInfos, List<String> tags);
}
