package com.cczywyc.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangyc
 */
@Entity
@Table(name = "t_order")
public class Order {
    /** 订单id */
    @Id
    @Column(name = "order_id")
    private int id;

    /** 用户id */
    @Column(name = "user_id")
    private int userId;

    /** 订单状态 */
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
