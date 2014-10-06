package com.example.test.executetype.repository;

import com.example.domain.model.Todo;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TodoRepository extends Repository {

    @Select({"SELECT * ",
            "FROM t_todo",
            "WHERE todo_id = #{todoId}"})
    Todo findOne(@Param("todoId") String todoId);

    @Insert({"INSERT INTO t_todo",
            "(todo_id, title, finished, created_at, version)",
            "VALUES(#{todoId},#{title},#{finished},#{createdAt},#{version})"})
    boolean create(Todo todo);

    @Insert({"INSERT INTO t_todo",
            "(todo_id, title, finished, created_at, version)",
            "VALUES(#{todoId},#{title},#{finished},#{createdAt},#{version})"})
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    boolean createOnNewTransaction(Todo todo);

    @Update({"UPDATE t_todo",
            "SET title = #{title}",
            ", finished = #{finished}",
            ", version = version + 1", "" +
            "WHERE todo_id = #{todoId}",
            "AND version = #{version}"})
    boolean update(Todo todo);

    @Delete({"DELETE FROM t_todo",
            "WHERE todo_id = #{todoId}",
            "AND version = #{version}"})
    boolean delete(Todo todo);

}
