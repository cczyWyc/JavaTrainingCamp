package com.cczyWyc.rpcfx_core.api;

import java.util.List;

/**
 * provider message
 *
 * @author wangyc
 */
public class ProviderInfo {
    /** provider id. zk generate id after registration */
    String id;
    /** provider back-end server address */
    String url;
    /** tag:simple route */
    List<String> tags;
    /** load balance */
    Integer weight;

    public ProviderInfo() {
    }

    public ProviderInfo(String id, String url, List<String> tags, Integer weight) {
        this.id = id;
        this.url = url;
        this.tags = tags;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
