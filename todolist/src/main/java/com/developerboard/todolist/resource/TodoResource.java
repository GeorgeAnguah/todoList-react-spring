package com.developerboard.todolist.resource;

import com.developerboard.todolist.exception.TodoResourceNotCreatedException;
import com.developerboard.todolist.exception.TodoResourceUnavailableException;
import com.developerboard.todolist.model.Todo;
import com.developerboard.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class Description
 *
 * @author Nana on 7/31/2019
 * @version 1.0
 * @see
 * @since 1.0
 */

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/todos")
public class TodoResource {

    private TodoService todoService;

    @Autowired
    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    Iterable<Todo> getTodoItems(){
        Iterable<Todo> allTodoItems = todoService.getAllTodoItems();
        if(allTodoItems.spliterator().getExactSizeIfKnown() == 0){
            throw new TodoResourceUnavailableException("Todo resource not available");
        }
        return allTodoItems;
    }

    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodo(@PathVariable Long id) {
        Todo todoById = todoService.getTodoById(id);
        if (todoById == null){
            throw new TodoResourceUnavailableException("Todo item with id " + id + " does not exist");
        }
         return ResponseEntity.ok(todoById);
    }

    @PostMapping
    ResponseEntity<?> createTodoItem(@Valid  @RequestBody Todo todo, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                String error = Objects.requireNonNull(bindingResult.getFieldError("name")).getDefaultMessage();
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            Todo todo1 = todoService.saveOrUpdate(todo);
            if(todo1 == null) {
                return ResponseEntity.noContent().build();
            }
            return new ResponseEntity<>(todo1, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("exception internal => " + e.getMessage());
            throw new TodoResourceNotCreatedException(e.getMessage());
        }
    }

    @PutMapping
    ResponseEntity<Todo> editTodoItem(@Valid @RequestBody Todo todo) {
        Todo todo1 = todoService.saveOrUpdate(todo);
        if(todo1 == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todo1);
    }

    @DeleteMapping("/{id}")
    void removeTodoItem(@PathVariable Long id) {
        todoService.deleteTodoById(id);
    }


}
