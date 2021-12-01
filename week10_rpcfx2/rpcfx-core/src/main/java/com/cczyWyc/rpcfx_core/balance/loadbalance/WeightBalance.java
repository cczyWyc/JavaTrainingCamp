package com.cczyWyc.rpcfx_core.balance.loadbalance;

import com.cczyWyc.rpcfx_core.api.ProviderInfo;

import java.util.List;
import java.util.Random;

/**
 * weight balance
 *
 * @author wangyc
 */
public class WeightBalance extends AbstractLoadBalance {

    /** load balance */
    private static final String NAME = "weight_balance";

    @Override
    public String select(List<ProviderInfo> providerInfos, String serviceName, String methodName) {
        int totalWeight = 0;
        for (ProviderInfo providerInfo : providerInfos) {
            totalWeight += providerInfo.getWeight();
        }

        int random = new Random().nextInt(totalWeight);
        System.out.printf("provider amount: %s, random : %d\n", providerInfos.size(), random);
        for (ProviderInfo providerInfo : providerInfos) {
            random -= providerInfo.getWeight();
            if (random >= 0) {
                return providerInfo.getUrl();
            }
        }
        return providerInfos.get(providerInfos.size() - 1).getUrl();
    }
}
