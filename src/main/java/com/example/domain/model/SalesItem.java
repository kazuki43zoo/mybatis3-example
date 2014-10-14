package com.example.domain.model;

import java.io.Serializable;

/**
 * Created by shimizukazuki on 2014/10/12.
 */
public class SalesItem implements Serializable {
    private Integer id;
    private Integer quantity;
    private Integer total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
