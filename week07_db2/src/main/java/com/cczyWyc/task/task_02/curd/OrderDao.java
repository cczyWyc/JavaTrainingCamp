package com.cczyWyc.task.task_02.curd;

import com.cczyWyc.task.task_02.entity.Order;

import java.util.List;

/**
 * order dao
 *
 * @author wangyc
 */
public interface OrderDao {
    /**
     * insert 100w order
     *
     * @param orders orders
     */
    public void insert(List<Order> orders);

    /**
     * insert 100w orders batch,once commit
     *
     * @param orders orders
     */
    public void insertBatch(List<Order> orders);

    /**
     * insert 100w orders batch,commit multiple times
     *
     * @param orders
     */
    public void insertBatchMultiple(List<Order> orders);
}
