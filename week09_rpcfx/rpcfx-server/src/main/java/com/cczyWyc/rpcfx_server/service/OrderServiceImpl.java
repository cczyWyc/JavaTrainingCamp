package com.cczyWyc.rpcfx_server.service;

import com.cczyWyc.rpcfx_api.model.Order;
import com.cczyWyc.rpcfx_api.service.OrderService;
import com.cczyWyc.rpcfx_core.exception.CustomException;

/**
 * order implement
 *
 * @author wangyc
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public Order findById(Integer integer) {
        return new Order(1, "RPC", 1);
    }

    @Override
    public Order findError() {
        throw new CustomException("Custom exception");
    }
}
