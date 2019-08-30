package com.developerboard.todolist.service;

import com.developerboard.todolist.model.Todo;
import org.springframework.stereotype.Service;

/**
 * Class Description
 *
 * @author Nana on 7/31/2019
 * @version 1.0
 * @see
 * @since 1.0
 */
@Service
public interface TodoService {

    Todo saveOrUpdate(Todo todo);

    Iterable<Todo> getAllTodoItems();

    Todo getTodoById(Long id);

    void deleteTodoById(Long id);
}
