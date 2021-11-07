package com.cczywyc.task.service.impl;

import com.cczywyc.task.entity.Order;
import com.cczywyc.task.repository.OrderRepository;
import com.cczywyc.task.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyc
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void insertBatch(List<Order> orderList) {
        orderRepository.saveAll(orderList);
    }
}
