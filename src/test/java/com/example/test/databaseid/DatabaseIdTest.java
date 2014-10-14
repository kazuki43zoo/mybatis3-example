package com.example.test.databaseid;

import com.example.domain.model.Todo;
import com.example.domain.repository.todo.TodoRepository;
import org.hamcrest.core.Is;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/example/test/databaseid/DatabaseIdTest.xml"})
public class DatabaseIdTest {

    @Inject
    @Named("h2TodoRepository")
    TodoRepository h2TodoRepository;

    @Inject
    @Named("postgresqlTodoRepository")
    TodoRepository postgresqlTodoRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void createOnH2() {
        Todo todo = new Todo();
        todo.setTitle("h2 title");
        todo.setFinished(false);
        todo.setCreatedAt(new Date());
        todo.setVersion(1);

        h2TodoRepository.create(todo);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void createOnPostgreSQL() {
        Todo todo = new Todo();
        todo.setTitle("h2 title");
        todo.setFinished(false);
        todo.setCreatedAt(new Date());
        todo.setVersion(1);

        postgresqlTodoRepository.save(todo);
        postgresqlTodoRepository.save(todo);

        todo.setTodoId(null);

        postgresqlTodoRepository.save(todo);
        postgresqlTodoRepository.save(todo);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void findOnH2() {

        Date currentDate = LocalDate.parse("2014-02-12").toDate();

        h2TodoRepository.findAllByCreatedAtBefore(10, currentDate);

    }


    @Test
    @Transactional
    @Rollback(false)
    public void findOnPostgreSQL() {

        Date currentDate = LocalDate.parse("2014-02-12").toDate();

        postgresqlTodoRepository.findAllByCreatedAtBefore(10, currentDate);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void findOne() {

        SecurityContextHolder.setContext(new SecurityContext() {
            private Authentication auth = new UsernamePasswordAuthenticationToken("user-" + UUID.randomUUID().toString(), "pass");

            @Override
            public Authentication getAuthentication() {
                return auth;
            }

            @Override
            public void setAuthentication(Authentication authentication) {
            }
        });

        Todo newTodo = new Todo();
        newTodo.setTitle("stored");
        newTodo.setFinished(false);

        System.out.println(newTodo.isNew());

        postgresqlTodoRepository.create(newTodo);

        System.out.println(newTodo.getCreatedDate());
        System.out.println(newTodo.getCreatedBy());
        System.out.println(newTodo.getLastModifiedBy());
        System.out.println(newTodo.getLastModifiedDate());
        System.out.println(newTodo.getId());
        System.out.println(newTodo.isNew());

        newTodo.setFinished(true);

        postgresqlTodoRepository.update(newTodo);

        System.out.println(newTodo.getCreatedDate());
        System.out.println(newTodo.getCreatedBy());
        System.out.println(newTodo.getLastModifiedBy());
        System.out.println(newTodo.getLastModifiedDate());
        System.out.println(newTodo.getVersion());

        Todo todo = postgresqlTodoRepository.findOne(newTodo.getTodoId());

        Assert.assertThat(todo.getTodoId(), Is.is(newTodo.getTodoId()));
        Assert.assertThat(todo.getTitle(), Is.is(newTodo.getTitle()));
        Assert.assertThat(todo.isFinished(), Is.is(newTodo.isFinished()));
        Assert.assertThat(todo.getCreatedAt(), Is.is(newTodo.getCreatedDate().toDate()));
        Assert.assertThat(todo.getVersion(), Is.is(newTodo.getVersion()));

    }

    @Test
    @Transactional
    @Rollback(false)
    public void findAllReturnMap() {

//        Map<String, Todo> todos = postgresqlTodoRepository.findAll();
//
//        System.out.println(todos.getClass());
//        System.out.println(todos);

    }


}
