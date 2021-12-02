package com.cczyWyc.rpcfx_core.filter.client;

import com.cczyWyc.rpcfx_core.api.ProviderInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * filter Line
 *
 * @author wangyc
 */
public class FilterLine {
    /** is init */
    private static boolean isInit = false;
    /** rpcFilter list */
    private static List<RpcFilter> rpcFilters = new ArrayList<>();

    public static void addFilter(RpcFilter rpcFilter) {
        rpcFilters.add(rpcFilter);
    }

    public static void init() {
        addFilter(new TagFilter());
    }

    public static List<ProviderInfo> filter(List<ProviderInfo> providerInfos, List<String> tags) {
        if (!isInit) {
            init();
            isInit = true;
        }

        List<ProviderInfo> filterResult = new ArrayList<>(providerInfos);
        for (RpcFilter rpcFilter : rpcFilters) {
            filterResult = rpcFilter.filter(filterResult, tags);
        }
        System.out.printf("\n%s filter to %s\n", providerInfos, filterResult);
        return filterResult;
    }
}
