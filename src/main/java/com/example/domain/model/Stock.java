package com.example.domain.model;

import java.io.Serializable;

public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;
    private String itemCode;
    private int quantity;
    private long version;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
