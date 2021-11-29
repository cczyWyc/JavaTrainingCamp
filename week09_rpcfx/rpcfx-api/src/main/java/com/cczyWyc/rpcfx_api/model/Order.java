package com.cczyWyc.rpcfx_api.model;

/**
 * @author wangyc
 */
public class Order {
    /** order id */
    private Integer id;
    /** order name */
    private String name;
    /** user id */
    private Integer userId;

    public Order(Integer id, String name, Integer userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
