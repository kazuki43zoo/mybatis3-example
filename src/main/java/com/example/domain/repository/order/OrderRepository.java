package com.example.domain.repository.order;

import com.example.domain.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepository {

    Order findOne(int id);

    List<Order> findPage(@Param("pageable") Pageable pageable);

}
