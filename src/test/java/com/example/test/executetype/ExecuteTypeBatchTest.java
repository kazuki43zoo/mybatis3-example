package com.example.test.executetype;

import com.example.domain.model.Todo;
import com.example.test.executetype.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/example/test/executetype/ExecuteTypeBatchTest.xml"})
public class ExecuteTypeBatchTest {

    @Inject
    TodoRepository todoRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void batch() {

        boolean result;

        Todo todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        result = todoRepository.create(todo);
        assertThat(result, is(false));

        todo.setTodoId(UUID.randomUUID().toString());
        result = todoRepository.create(todo);
        assertThat(result, is(false));

        todo.setTodoId(UUID.randomUUID().toString());
        result = todoRepository.create(todo);
        assertThat(result, is(false));

    }

}
