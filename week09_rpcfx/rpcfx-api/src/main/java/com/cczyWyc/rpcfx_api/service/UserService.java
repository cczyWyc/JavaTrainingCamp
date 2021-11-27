package com.cczyWyc.rpcfx_api.service;

import com.cczyWyc.rpcfx_api.model.User;

/**
 * @author wangyc
 */
public interface UserService {
    /**
     * find user by id
     *
     * @param id user is
     * @return user
     */
    User findById(Integer id);
}
