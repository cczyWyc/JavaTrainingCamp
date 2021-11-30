package com.cczywyc.rpcfxserver.service.impl;

import com.cczyWyc.rpcfx_api.model.User;
import com.cczyWyc.rpcfx_api.service.UserService;

/**
 * user service implement
 *
 * @author wangyc
 */
public class UserServiceImpl implements UserService {
    @Override
    public User findById(Integer integer) {
        return new User(1, "RPC");
    }
}
