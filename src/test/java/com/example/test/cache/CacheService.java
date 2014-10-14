package com.example.test.cache;

import com.example.domain.model.Todo;
import com.example.test.cache.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by shimizukazuki on 2014/10/10.
 */
@Service
public class CacheService {

    @Inject
    TodoRepository repository;

//    @Transactional(propagation = Propagation.REQUIRED)
    public Todo getTodo(String id){
        System.out.println("★★★★★★★1★★★★★★★★");
        System.out.println(repository.findOne(id));
        System.out.println("★★★★★★★2★★★★★★★★");
        return repository.findOne(id);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Todo getTodoWithNewTran(String id){
        System.out.println("★★★★★★★3★★★★★★★★");
        System.out.println(repository.findOne(id));
        System.out.println("★★★★★★★4★★★★★★★★");
        return repository.findOne(id);
    }

}
