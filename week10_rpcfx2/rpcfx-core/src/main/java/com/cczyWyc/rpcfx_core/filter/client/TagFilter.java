package com.cczyWyc.rpcfx_core.filter.client;

import com.cczyWyc.rpcfx_core.api.ProviderInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * tag filter. simple route
 *
 * @author wangyc
 */
public class TagFilter implements RpcFilter {
    @Override
    public List<ProviderInfo> filter(List<ProviderInfo> providerInfos, List<String> tags) {
        System.out.printf("\n%s tag filter start :: %s \n", tags.toString(), providerInfos);
        if (tags.isEmpty()) {
            return providerInfos;
        }
        List<ProviderInfo> newProviders = new ArrayList<>(providerInfos.size());
        for (ProviderInfo providerInfo : providerInfos) {
            for (String tag : tags) {
                if (providerInfo.getTags().contains(tag)) {
                    newProviders.add(providerInfo);
                    break;
                }
            }
        }
        return newProviders;
    }
}
