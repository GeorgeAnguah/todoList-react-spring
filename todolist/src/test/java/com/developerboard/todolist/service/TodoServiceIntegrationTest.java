package com.developerboard.todolist.service;

import com.developerboard.todolist.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Class Description
 *
 * @author Nana on 8/10/2019
 * @version 1.0
 * @see
 * @since 1.0
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TodoServiceIntegrationTest {

    @Autowired
    private TodoService todoService;

    @Test
    void testSaveTodoHappyPath() {

        // Create a new todo
        Todo todo = new Todo("Pay credit card debt", new Date(), true) ;

        // Test adding the todo
        Todo newTodo = todoService.saveOrUpdate(todo);

        // Verify saved todo item
        assertNotNull(newTodo);
        assertNotNull(newTodo.getId());
        assertEquals("Pay credit card debt", newTodo.getName());

    }
}
