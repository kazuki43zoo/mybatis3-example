package com.example.test.executetype;

import com.example.domain.model.Todo;
import com.example.test.executetype.repository.TodoBulkRepository;
import com.example.test.executetype.repository.TodoRepository;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/example/test/executetype/ExecuteTypeTest.xml"})
public class ExecuteTypeTest {

    @Inject
    TodoRepository todoRepository;

    @Inject
    @Named("todoReuseRepository")
    TodoRepository todoReuseRepository;

    @Inject
    @Named("todoBatchRepository")
    TodoRepository todoBatchRepository;

    @Inject
    TodoBulkRepository todoBulkRepository;

    @Inject
    @Named("batchSqlSessionTemplate")
    SqlSession sqlSession;


    @Test
    @Transactional
    @Rollback(false)
    public void simple() {

        boolean result;

        Todo todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        result = todoRepository.create(todo);
        assertThat(result, is(true));

        todo.setTodoId(UUID.randomUUID().toString());
        result = todoRepository.create(todo);
        assertThat(result, is(true));

        todo.setTodoId(UUID.randomUUID().toString());
        result = todoRepository.create(todo);
        assertThat(result, is(true));

    }

    @Test
    @Transactional
    @Rollback(false)
    public void reuse() {

        boolean result;

        Todo todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        result = todoReuseRepository.create(todo);
        assertThat(result, is(true));

        todo.setTodoId(UUID.randomUUID().toString());
        result = todoReuseRepository.create(todo);
        assertThat(result, is(true));

        todo.setTodoId(UUID.randomUUID().toString());
        result = todoReuseRepository.create(todo);
        assertThat(result, is(true));

    }

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
        result = todoBatchRepository.create(todo);
        assertThat(result, is(false));

        todo.setTodoId(UUID.randomUUID().toString());
        result = todoBatchRepository.create(todo);
        assertThat(result, is(false));

        todo.setTodoId(UUID.randomUUID().toString());
        result = todoBatchRepository.create(todo);
        assertThat(result, is(false));

    }

    @Test
    @Transactional
    @Rollback(false)
    public void bulkUpdateSuccess() {
        Todo todo;

        List<Todo> list = new ArrayList<>();
        todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        list.add(todo);
        todoBatchRepository.create(todo);

        todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        list.add(todo);
        todoBatchRepository.create(todo);

        todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        list.add(todo);
        todoBatchRepository.create(todo);

        System.out.println("..............");

        int[] counts = todoBulkRepository.update(list);
        for (int count : counts) {
            assertThat(count, is(1));
        }

    }

    @Test
    @Transactional
    @Rollback(false)
    public void bulkUpdateOptimisticFail() {
        Todo todo;

        List<Todo> list = new ArrayList<>();
        todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        list.add(todo);
        todoBatchRepository.create(todo);

        todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        list.add(todo);
        todoBatchRepository.create(todo);

        todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        list.add(todo);
        todoBatchRepository.create(todo);

        System.out.println("..............");

        list.get(1).setVersion(2);


        int[] counts = todoBulkRepository.update(list);
        assertThat(counts[0], is(1));
        assertThat(counts[1], is(0));
        assertThat(counts[2], is(1));

    }


    @Test(expected = TransientDataAccessResourceException.class)
    @Transactional
    @Rollback(false)
    public void differenceExecuteType() {

        Todo todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        todoRepository.create(todo);

        todo.setTodoId(UUID.randomUUID().toString());
        todoBatchRepository.create(todo);


    }

    @Test
    @Transactional
    @Rollback(false)
    public void differenceExecuteTypeOnAnotherTransaction() {

        boolean result;

        Todo todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        result = todoBatchRepository.createOnNewTransaction(todo);
        assertThat(result, is(false));

        todo.setTodoId(UUID.randomUUID().toString());
        result = todoRepository.create(todo);
        assertThat(result, is(true));


    }


    @Test
    @Transactional
    public void batchAndFind() {

        Todo todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);
        todoBatchRepository.create(todo);

        Todo loadedTodo = todoBatchRepository.findOne(todo.getTodoId());

        assertNotNull(loadedTodo);

    }

    @Test(expected = DuplicateKeyException.class)
    @Transactional
    @Rollback(false)
    public void batchAndFindOne() {

        boolean result;

        Todo todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);

        result = todoBatchRepository.create(todo);
        assertThat(result, is(false));

        result = todoBatchRepository.create(todo);
        assertThat(result, is(false));

        sqlSession.flushStatements();

    }


    @Test
    @Transactional
    @Rollback(false)
    public void batchOrder() {

        Todo todo;
        boolean result;

        todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);

        result = todoBatchRepository.create(todo);
        assertThat(result, is(false));

        result = todoBatchRepository.delete(todo);
        assertThat(result, is(false));

        result = todoBatchRepository.update(todo);
        assertThat(result, is(false));

        result = todoBatchRepository.create(todo);
        assertThat(result, is(false));

        todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTitle("title");
        todo.setCreatedAt(new Date());
        todo.setFinished(false);
        todo.setVersion(1);

        result = todoBatchRepository.create(todo);
        assertThat(result, is(false));

        result = todoBatchRepository.delete(todo);
        assertThat(result, is(false));

        result = todoBatchRepository.update(todo);
        assertThat(result, is(false));

        result = todoBatchRepository.create(todo);
        assertThat(result, is(false));

    }
}
