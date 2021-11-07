package com.cczyWyc.task.task_02;

import com.cczyWyc.task.task_02.curd.impl.OrderDaoImpl;
import com.cczyWyc.task.task_02.entity.Order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * main
 *
 * @author wangyc
 */
public class BatchInsertMain {
    public static void main(String[] args) {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        List<Order> orderList = new ArrayList<>();
        for (int i = 1; i <= 1000000; i++) {
            Order order = new Order();
            order.setId(i);
            order.setOrderNo("SN00X");
            order.setUserId(1);
            order.setStatus(1);
            order.setLogisticsNo("SF0001");
            order.setGoodsNo(1);
            order.setPayMoney(22.02);
            order.setRealPayMoney(20.00);
            order.setFreightMoney(4.00);
            order.setPayPlatformNO("X00001");
            order.setDeleted(0);
            order.setPayTime(new Timestamp(System.currentTimeMillis()));
            order.setCreateTime(new Timestamp(System.currentTimeMillis()));
            order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            orderList.add(order);
        }
        long start = System.currentTimeMillis();
        orderDao.insertBatchMultiple(orderList);
        System.out.println("insert time:" + (System.currentTimeMillis() - start));
    }
}
