package com.developerboard.todolist.service.impl;

import com.developerboard.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.developerboard.todolist.repository.TodoRepository;
import com.developerboard.todolist.service.TodoService;

/**
 * Class Description
 *
 * @author Nana on 7/31/2019
 * @version 1.0
 * @see
 * @since 1.0
 */

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo saveOrUpdate(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Iterable<Todo> getAllTodoItems() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.getById(id);
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}
