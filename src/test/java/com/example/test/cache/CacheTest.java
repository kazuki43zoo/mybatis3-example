package com.example.test.cache;

import com.example.domain.model.Todo;
import com.example.test.cache.repository.TodoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/example/test/cache/CacheTest.xml"})
public class CacheTest {


    @Inject
    CacheService service;

    @Test
    @Transactional
    @Rollback(false)
    public void sameTran() {

        Todo todo;

        todo = service.getTodo("4d3c8bdd-5379-4aeb-bc56-fcb01eb7ccaa");

        todo.setTitle("hoge");
        System.out.println("---------------");

        todo = service.getTodo("4d3c8bdd-5379-4aeb-bc56-fcb01eb7ccaa");

        Assert.assertThat("hoge", is(todo.getTitle()));


    }

    @Test
    @Transactional
    @Rollback(false)
    public void anotherTran() {

        Todo todo;

        System.out.println("--------1-------");
        todo = service.getTodo("4d3c8bdd-5379-4aeb-bc56-fcb01eb7ccaa");
        System.out.println(todo);
        Assert.assertThat(todo.getTitle(), is("title"));
        todo.setTitle("1");

        todo = service.getTodo("4d3c8bdd-5379-4aeb-bc56-fcb01eb7ccaa");
        System.out.println(todo);
        Assert.assertThat(todo.getTitle(), is("1"));
        todo.setTitle("1-1");


        System.out.println("--------2-------");
        todo = service.getTodoWithNewTran("4d3c8bdd-5379-4aeb-bc56-fcb01eb7ccaa");
        System.out.println(todo);
        Assert.assertThat(todo.getTitle(), is("title"));
        todo.setTitle("2");

        System.out.println("--------3-------");
        todo = service.getTodoWithNewTran("4d3c8bdd-5379-4aeb-bc56-fcb01eb7ccaa");
        System.out.println(todo);
        Assert.assertThat(todo.getTitle(), is("title"));
        todo.setTitle("3");

        System.out.println("--------4-------");
        todo = service.getTodo("4d3c8bdd-5379-4aeb-bc56-fcb01eb7ccaa");
        System.out.println(todo);
        Assert.assertThat(todo.getTitle(), is("1-1"));
        todo.setTitle("4");

        System.out.println("--------5-------");
        todo = service.getTodo("4d3c8bdd-5379-4aeb-bc56-fcb01eb7ccaa");
        System.out.println(todo);
        Assert.assertThat(todo.getTitle(), is("4"));
        todo.setTitle("5");

    }


}
