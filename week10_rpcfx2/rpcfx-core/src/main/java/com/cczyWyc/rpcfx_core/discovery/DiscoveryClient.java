package com.cczyWyc.rpcfx_core.discovery;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cczyWyc.rpcfx_core.api.ProviderInfo;
import com.cczyWyc.rpcfx_core.balance.LoadBalance;
import com.cczyWyc.rpcfx_core.balance.loadbalance.ConsistentHashBalance;
import com.cczyWyc.rpcfx_core.balance.loadbalance.WeightBalance;
import com.cczyWyc.rpcfx_core.filter.client.FilterLine;
import com.cczyWyc.rpcfx_core.proxy.RpcClient;
import com.google.common.base.Joiner;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /** provider cache list */
    private Map<String, List<ProviderInfo>> providersCache = new HashMap<>();
    /** service discovery */
    private final ServiceDiscovery<ProviderInfo> serviceDiscovery;
    /** curator cache */
    private final CuratorCache resourceCache;
    /** load balance */
    private LoadBalance loadBalance = new WeightBalance();

    private DiscoveryClient() {
        serviceDiscovery = ServiceDiscoveryBuilder.builder(ProviderInfo.class)
                .client(client)
                .basePath("/" + REGISTER_ROOT_PATH)
                .build();

        try {
            serviceDiscovery.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            getAllProviders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.resourceCache = CuratorCache.build(this.client, "/");
        watchResources();

        if (RpcClient.getBalanceAlgorithmName().equals(WeightBalance.NAME)) {
            this.loadBalance = new WeightBalance();
        }
        else if (RpcClient.getBalanceAlgorithmName().equals(ConsistentHashBalance.NAME)) {
            this.loadBalance = new ConsistentHashBalance();
        }

    }

    /**
     * get all provider list from zookeeper
     *
     */
    private void getAllProviders() throws Exception {
        System.out.println("\n\n======================= init : get all provider");
        Collection<String> serviceNames = serviceDiscovery.queryForNames();
        System.out.println(serviceNames.size() + " type(s)");
        for (String serviceName : serviceNames) {
            Collection<ServiceInstance<ProviderInfo>> instances = serviceDiscovery.queryForInstances(serviceName);
            System.out.println(serviceName);

            for (ServiceInstance<ProviderInfo> instance : instances) {
                System.out.println(instance.toString());
                String url = "http://" + instance.getAddress() + ":" + instance.getPort();
                ProviderInfo providerInfo = instance.getPayload();
                providerInfo.setId(instance.getId());
                providerInfo.setUrl(url);

                List<ProviderInfo> providerInfoList = providersCache.getOrDefault(instance.getName(), new ArrayList<>());
                providerInfoList.add(providerInfo);
                providersCache.put(instance.getName(), providerInfoList);

                System.out.println("add provider: " + instance.toString());
            }
        }

        for(String key: providersCache.keySet()) {
            System.out.println(key + " : " + providersCache.get(key));
        }

        System.out.println("======================= init : get all provider end\n\n");
    }

    /**
     * get provider
     *
     * @param service service name
     * @param group group
     * @param version version
     * @param tags tags
     * @param methodName method name
     * @return providerInfo
     */
    public String getProviders(String service, String group, String version, List<String> tags, String methodName) {
        String provider = Joiner.on(":").join(service, group, version);
        if (!providersCache.containsKey(provider) || providersCache.get(provider).isEmpty()) {
            return null;
        }
        List<ProviderInfo> providerInfos = FilterLine.filter(providersCache.get(provider), tags);
        if (providerInfos.isEmpty()) {
            return null;
        }
        return loadBalance.select(providerInfos, service, methodName);
    }

    /**
     * listen provider
     *
     */
    private void watchResources() {
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forCreates(this::addHandler)
                .forChanges(this::changeHandler)
                .forDeletes(this::deleteHandler)
                .forInitialized(() -> System.out.println("Resources Cache initialized"))
                .build();
        resourceCache.listenable().addListener(listener);
        resourceCache.start();
    }

    /**
     * add Provider
     * @param node new provider
     */
    private void addHandler(ChildData node) {
        System.out.println("\n\n=================== add new provider ============================");

        System.out.printf("Node created: [%s:%s]%n", node.getPath(), new String(node.getData()));
        if (providerDataEmpty(node)) {
            return;
        }

        updateProvider(node);

        System.out.println("=================== add new provider end ============================\n\n");
    }

    /**
     * update provider
     * @param oldNode old provider
     * @param newNode updated provider
     */
    private void changeHandler(ChildData oldNode, ChildData newNode) {
        System.out.printf("Node changed, Old: [%s: %s] New: [%s: %s]%n", oldNode.getPath(),
                new String(oldNode.getData()), newNode.getPath(), new String(newNode.getData()));

        if (providerDataEmpty(newNode)) {
            return;
        }

        updateProvider(newNode);
    }

    /**
     * add or update local Provider
     * @param newNode updated provider
     */
    private void updateProvider(ChildData newNode) {
        String jsonValue = new String(newNode.getData(), StandardCharsets.UTF_8);
        JSONObject instance = (JSONObject) JSONObject.parse(jsonValue);
        System.out.println(instance.toString());

        String url = "http://" + instance.get("address") + ":" + instance.get("port");
        ProviderInfo providerInfo = JSON.parseObject(instance.get("payload").toString(), ProviderInfo.class);
        providerInfo.setId(instance.get("id").toString());
        providerInfo.setUrl(url);

        List<ProviderInfo> providerList = providersCache.getOrDefault(instance.get("name").toString(), new ArrayList<>());
        providerList.add(providerInfo);
        providersCache.put(instance.get("name").toString(), providerList);
    }

    /**
     * delete provider
     * @param oldNode provider
     */
    private void deleteHandler(ChildData oldNode) {
        System.out.println("\n\n=================== delete provider ============================");

        System.out.printf("Node deleted, Old value: [%s: %s]%n", oldNode.getPath(), new String(oldNode.getData()));
        if (providerDataEmpty(oldNode)) {
            return;
        }

        String jsonValue = new String(oldNode.getData(), StandardCharsets.UTF_8);
        JSONObject instance = (JSONObject) JSONObject.parse(jsonValue);
        System.out.println(instance.toString());

        String provider = instance.get("name").toString();
        int deleteIndex = -1;
        for (int i = 0; i < providersCache.get(provider).size(); i++) {
            if (providersCache.get(provider).get(i).getId().equals(instance.get("id").toString())) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex != -1) {
            providersCache.get(provider).remove(deleteIndex);
        }

        System.out.println("=================== delete provider end ============================\n\n");
    }

    private boolean providerDataEmpty(ChildData node) {
        return node.getData().length == 0;
    }

    public synchronized void close() {
        client.close();
    }
}
