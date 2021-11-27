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
}
