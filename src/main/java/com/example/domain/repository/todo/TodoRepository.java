package com.example.domain.repository.todo;

import com.example.domain.model.Todo;
import com.example.domain.repository.GenericRepository;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TodoRepository extends GenericRepository<Todo, String> {

    int updateFinishedByTodIds(@Param("finished") boolean finished,
                               @Param("todoIds") List<String> todoIds);

    int deleteOlderFinishedTodo(Date criteriaDate);

    List<Todo> findByFnished(boolean finished);

    List<Todo> findAllByCreatedAtBefore(@Param("days") int days,
                                        @Param("currentDate") Date currentDate);


}
