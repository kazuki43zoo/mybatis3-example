package com.example.test.delete;

import com.example.domain.model.Todo;
import com.example.domain.repository.todo.TodoRepository;
import org.hamcrest.core.Is;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/example/test/delete/DeleteTest.xml"})
public class DeleteTest {

    @Inject
    TodoRepository todoRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void deleteOlderFinishedTodo() {

        List<String> todoIds = Arrays.asList(
                "4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca2",
                "4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca4",
                "4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca6",
                "4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca8"
        );

        Date criteriaDate = LocalDate.parse("2014-02-07").toDate();

        int deleteCount = todoRepository.deleteOlderFinishedTodo(criteriaDate);

        Assert.assertThat(deleteCount, Is.is(4));

        List<Todo>  unfinishedTodos = todoRepository.findByFnished(false);
        Assert.assertThat(unfinishedTodos.get(0).getTodoId(), Is.is("4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc10"));
        Assert.assertThat(unfinishedTodos.get(1).getTodoId(), Is.is("4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca2"));
        Assert.assertThat(unfinishedTodos.get(2).getTodoId(), Is.is("4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca4"));
        Assert.assertThat(unfinishedTodos.get(3).getTodoId(), Is.is("4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca6"));
        Assert.assertThat(unfinishedTodos.get(4).getTodoId(), Is.is("4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca8"));
        Assert.assertThat(unfinishedTodos.get(5).getTodoId(), Is.is("4d3c8bdd-5379-4aeb-bc56-fcb01eb7ccaa"));

        List<Todo> finishedTodos = todoRepository.findByFnished(true);
        Assert.assertThat(finishedTodos.get(0).getTodoId(), Is.is("4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca9"));

    }

}
