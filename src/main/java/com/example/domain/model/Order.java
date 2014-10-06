package com.example.domain.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private OrderStatus orderStatus;
    List<OrderItem> orderItems;
    List<OrderCoupon> orderCoupons;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderCoupon> getOrderCoupons() {
        return orderCoupons;
    }

    public void setOrderCoupons(List<OrderCoupon> orderCoupons) {
        this.orderCoupons = orderCoupons;
    }
}
