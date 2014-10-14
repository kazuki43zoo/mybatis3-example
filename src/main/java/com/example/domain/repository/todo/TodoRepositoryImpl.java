package com.example.domain.repository.todo;

import com.example.domain.model.Todo;
import com.example.domain.repository.GenericRepositoryImplProxySupport;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepositoryImpl extends GenericRepositoryImplProxySupport<TodoRepository, Todo, String> {
}
