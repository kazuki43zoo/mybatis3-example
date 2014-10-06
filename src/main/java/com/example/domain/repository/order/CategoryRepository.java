package com.example.domain.repository.order;

import com.example.domain.model.Category;

import java.util.List;

public interface CategoryRepository {

    Category findOne(String code);

    List<Category> findAllByItemCode(String itemCode);

}
