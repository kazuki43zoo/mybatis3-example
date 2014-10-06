package com.example.domain.model;

import java.io.Serializable;

public class OrderCoupon implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderId;
    private Coupon coupon;
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public Coupon getCoupon() {
        return coupon;
    }
    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}
