package com.cczywyc.task.service;

import com.cczywyc.task.entity.Order;

import java.util.List;

/**
 * @author wangyc
 */
public interface OrderService {

    /**
     * insert
     *
     * @param order order
     */
    void insert(Order order);

    /**
     * insert all
     *
     * @param orders orders
     */
    void insertAll(List<Order> orders);
}
