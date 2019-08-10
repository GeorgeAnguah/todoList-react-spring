package com.developerboard.todolist.controller;

import com.developerboard.todolist.model.Todo;
import com.developerboard.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Class Description
 *
 * @author Nana on 7/31/2019
 * @version 1.0
 * @see
 * @since 1.0
 */

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    Iterable<Todo> getTodoItems(){
        return todoService.getAllTodoItems();
    }

    @GetMapping("/{id}")
    Todo getTodo(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @PostMapping
    Todo createTodoItem(@RequestBody Todo todo) {
        return todoService.saveOrUpdate(todo);
    }

    @PutMapping
    Todo editTodoItem(@RequestBody Todo todo) {
        return todoService.saveOrUpdate(todo);
    }

    @DeleteMapping("/{id}")
    void removeTodoItem(@PathVariable Long id){
        todoService.deleteTodoById(id);
    }


}
