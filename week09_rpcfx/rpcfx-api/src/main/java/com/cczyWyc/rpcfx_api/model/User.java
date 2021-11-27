package com.cczyWyc.rpcfx_api.model;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangyc
 */
@Slf4j
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
