package com.example.domain.model;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private int price;
    private List<Category> categories;
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
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
