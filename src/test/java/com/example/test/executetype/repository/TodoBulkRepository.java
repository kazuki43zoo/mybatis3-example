package com.example.test.executetype.repository;

import com.example.domain.model.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TodoBulkRepository {

    int[] update(List<Todo> todos);

}
