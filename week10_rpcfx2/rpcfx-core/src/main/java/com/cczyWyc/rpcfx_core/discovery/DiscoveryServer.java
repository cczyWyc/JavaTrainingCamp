package com.cczyWyc.rpcfx_core.discovery;

import com.cczyWyc.rpcfx_core.api.ProviderInfo;
import com.google.common.base.Joiner;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * server discovery. register provider
 *
 * @author wangyc
 */
public class DiscoveryServer extends ZookeeperClient {

    /** discovery list */
    private List<ServiceDiscovery<ProviderInfo>> discoveryList = new ArrayList<>();

    public DiscoveryServer() {
    }

    /**
     * generate provider info, register into zk
     *
     * @param service service impl name
     * @param group group
     * @param version version
     * @param port service listen port
     * @param tags route tag
     * @param weight load balance weisht
     * @throws Exception ex
     */
    public void registerService(String service, String group, String version, int port, List<String> tags,
                                int weight) throws Exception {
        ProviderInfo providerInfo = new ProviderInfo(null, null, tags, weight);
        ServiceInstance<ProviderInfo> instance = ServiceInstance.<ProviderInfo>builder()
                .name(Joiner.on(":").join(service, group, version))
                .port(port)
                .address(InetAddress.getLocalHost().getHostAddress())
                .payload(providerInfo)
                .build();
        JsonInstanceSerializer<ProviderInfo> jsonInstanceSerializer = new JsonInstanceSerializer<>(ProviderInfo.class);
        ServiceDiscovery<ProviderInfo> discovery = ServiceDiscoveryBuilder.builder(ProviderInfo.class)
                .client(client)
                .basePath(REGISTER_ROOT_PATH)
                .thisInstance(instance)
                .serializer(jsonInstanceSerializer)
                .build();
        discovery.start();

        discoveryList.add(discovery);
    }

    /**
     * clise resource
     *
     * @throws IOException IO ex
     */
    public void close() throws IOException {
        for (ServiceDiscovery<ProviderInfo> serviceDiscovery : discoveryList) {
            serviceDiscovery.close();
        }
        client.close();
    }

}
