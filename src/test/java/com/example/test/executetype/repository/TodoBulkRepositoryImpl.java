package com.example.test.executetype.repository;

import com.example.domain.model.Todo;
import org.apache.ibatis.executor.BatchResult;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Repository
public class TodoBulkRepositoryImpl extends SqlSessionDaoSupport implements TodoBulkRepository {

    private static final int[] EMPTY_RESULT = new int[0];

    @Inject
    @Named("todoBatchRepository")
    TodoRepository todoRepository;

    @Inject
    @Named("batchSqlSessionTemplate")
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int[] update(List<Todo> todos) {
        getSqlSession().flushStatements();
        if (todos == null || todos.isEmpty()) {
            return EMPTY_RESULT;
        }
        for (Todo todo : todos) {
            todoRepository.update(todo);
        }
        BatchResult result = getSqlSession().flushStatements().iterator().next();
        return result.getUpdateCounts();
    }

}
