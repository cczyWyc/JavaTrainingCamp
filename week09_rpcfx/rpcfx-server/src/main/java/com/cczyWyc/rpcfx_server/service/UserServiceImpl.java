package com.cczyWyc.rpcfx_server.service;

import com.cczyWyc.rpcfx_api.model.User;
import com.cczyWyc.rpcfx_api.service.UserService;

/**
 * user implement
 *
 * @author wangyc
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findById(Integer integer) {
        //Specific server-side business realization
        return new User(1, "RPC");
    }
}
