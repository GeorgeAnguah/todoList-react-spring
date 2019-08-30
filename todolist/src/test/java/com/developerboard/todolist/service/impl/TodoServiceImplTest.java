package com.developerboard.todolist.service.impl;

import com.developerboard.todolist.model.Todo;
import com.developerboard.todolist.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Class Description
 *
 * @author Nana on 8/10/2019
 * @version 1.0
 * @see
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {

    @InjectMocks
    private TodoServiceImpl todoService;

    @Mock
    private TodoRepository todoRepository;


    @Test
    void testSaveTodoHappyPath() {

        // Create a todo
        Todo aMockTodo = new Todo("Wash the car", new Date(), true);

        when(todoRepository.save(any(Todo.class))).thenReturn(aMockTodo);

        // save the todo
        Todo newTodo = todoService.saveOrUpdate(new Todo());

        // Verify the save
        Assertions.assertNotNull(newTodo);
        assertEquals("Wash the car", newTodo.getName());

    }

    @Test
    void testGetTodoById() {
        // Create a todo
        Todo aMockTodo = new Todo("Wash the car", new Date(), false);
        aMockTodo.setId(1L);

        when(todoRepository.getById(1L)).thenReturn(aMockTodo);

        Todo returnedTodo = todoService.getTodoById(1L);

        // Verify
        assertEquals(Long.valueOf(1), returnedTodo.getId());
        assertEquals("Wash the car", returnedTodo.getName());
        assertEquals(false, returnedTodo.isCompleted());
    }

}