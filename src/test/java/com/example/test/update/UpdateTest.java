package com.example.test.update;

import com.example.domain.model.Todo;
import com.example.domain.repository.todo.TodoRepository;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/example/test/update/UpdateTest.xml"})
public class UpdateTest {

    @Inject
    TodoRepository todoRepository;


    @Test
    @Transactional
    @Rollback(false)
    public void updateFinishedByTodoIds() {

        List<String> todoIds = Arrays.asList(
                "4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca2",
                "4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca4",
                "4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca6",
                "4d3c8bdd-5379-4aeb-bc56-fcb01eb7cca8"
        );

        int updateCount = todoRepository.updateFinishedByTodIds(true, todoIds);

        Assert.assertThat(updateCount, Is.is(4));

        List<Todo> finishedTodos = todoRepository.findByFnished(true);
        Assert.assertThat(finishedTodos.size(), Is.is(4));
        Assert.assertThat(finishedTodos.get(0).getVersion(), Is.is(3L));
        Assert.assertThat(finishedTodos.get(1).getVersion(), Is.is(5L));
        Assert.assertThat(finishedTodos.get(2).getVersion(), Is.is(7L));
        Assert.assertThat(finishedTodos.get(3).getVersion(), Is.is(9L));

        List<Todo> unfinishedTodos = todoRepository.findByFnished(false);
        Assert.assertThat(unfinishedTodos.size(), Is.is(7));

    }


}
