package com.cczywyc.task.run;

import com.cczywyc.task.entity.Order;
import com.cczywyc.task.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyc
 */
@Component
public class InsertMain implements ApplicationRunner {
    @Autowired
    OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
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
        orderService.insertBatch(orderList);
        System.out.println("insert time:" + (System.currentTimeMillis() - start));
    }
}
