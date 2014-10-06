package com.example.domain.model;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private List<Item> items;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
