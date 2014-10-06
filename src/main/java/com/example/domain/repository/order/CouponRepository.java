package com.example.domain.repository.order;

import com.example.domain.model.Item;

import java.util.List;

public interface CouponRepository {

    Item findOne(String code);

}
