package com.example.test.cache.repository;

import com.example.domain.model.Todo;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TodoRepository extends Repository {

    Todo findOne(@Param("todoId") String todoId);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Todo findOneWithNewTransaction(@Param("todoId") String todoId);

}
