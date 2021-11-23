package com.cczywyc.task.test;

import com.cczywyc.task.entity.Order;
import com.cczywyc.task.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangyc
 */
@Component
public class Main implements ApplicationRunner {

    @Autowired
    private OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Order order = new Order();
        order.setId(1);
        order.setUserId(1);
        order.setStatus("F");
        orderService.insert(order);
    }
}
