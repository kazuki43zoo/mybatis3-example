package com.example.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

public interface GenericRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    void create(T entity);

    boolean update(T entity);

}
