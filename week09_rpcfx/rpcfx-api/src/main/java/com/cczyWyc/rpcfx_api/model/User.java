package com.cczyWyc.rpcfx_api.model;

/**
 * @author wangyc
 */
public class User {
    /** user id */
    private Integer id;
    /** username */
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
