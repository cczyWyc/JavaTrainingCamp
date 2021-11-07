package com.cczyWyc.task.task_02.entity;

import java.sql.Timestamp;

/**
 * order entity
 *
 * @author wangyc
 */
public class Order {
    /** order id */
    private int id;
    /** order number */
    private String orderNo;
    /** user id */
    private int userId;
    /** order status */
    private int status;
    /** logistics number */
    private String logisticsNo;
    /** goods amount number */
    private int goodsNo;
    /** should pay money */
    private double payMoney;
    /** real pay monet */
    private double realPayMoney;
    /** freight money */
    private double freightMoney;
    /** Payment platform serial number */
    private String payPlatformNO;
    /** is deleted */
    private int deleted;
    /** order pay time */
    private Timestamp payTime;
    /** order create time */
    private Timestamp createTime;
    /** order update time */
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public int getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(int goodsNo) {
        this.goodsNo = goodsNo;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public double getRealPayMoney() {
        return realPayMoney;
    }

    public void setRealPayMoney(double realPayMoney) {
        this.realPayMoney = realPayMoney;
    }

    public double getFreightMoney() {
        return freightMoney;
    }

    public void setFreightMoney(double freightMoney) {
        this.freightMoney = freightMoney;
    }

    public String getPayPlatformNO() {
        return payPlatformNO;
    }

    public void setPayPlatformNO(String payPlatformNO) {
        this.payPlatformNO = payPlatformNO;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", status=" + status +
                ", logisticsNo='" + logisticsNo + '\'' +
                ", goodsNo=" + goodsNo +
                ", payMoney=" + payMoney +
                ", realPayMoney=" + realPayMoney +
                ", freightMoney=" + freightMoney +
                ", payPlatformNO='" + payPlatformNO + '\'' +
                ", deleted=" + deleted +
                ", payTime=" + payTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
