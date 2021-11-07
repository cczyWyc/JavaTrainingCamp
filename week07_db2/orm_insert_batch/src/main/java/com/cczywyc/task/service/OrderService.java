package com.cczywyc.task.service;

import com.cczywyc.task.entity.Order;

import java.util.List;

/**
 * @author wangyc
 */
public interface OrderService {
    /**
     * insert batch
     *
     * @param orderList order lists
     */
    void insertBatch(List<Order> orderList);
}
