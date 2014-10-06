package com.example.domain.repository.order;

import com.example.domain.model.Item;

import java.util.List;

public interface ItemRepository {

    Item findOne(String code);

    List<Item> findAllByCategoryCode(String categoryCode);

}
