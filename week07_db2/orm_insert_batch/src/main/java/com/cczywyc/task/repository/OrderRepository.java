package com.cczywyc.task.repository;

import com.cczywyc.task.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangyc
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
