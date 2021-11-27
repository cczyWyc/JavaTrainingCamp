package com.cczyWyc.rpcfx_api.service;

import com.cczyWyc.rpcfx_api.model.Order;

/**
 * @author wangyc
 */
public interface OrderService {
    /**
     * find order by id
     *
     * @param id order id
     * @return order
     */
    Order findById(Integer id);

    /**
     * return ex
     *
     * @return ex
     */
    Order findError();
}
