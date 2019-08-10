package com.developerboard.todolist;

import com.developerboard.todolist.model.Todo;
import com.developerboard.todolist.repository.TodoRepository;
import com.developerboard.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class TodolistApplication {

    //Inject TodoService
    @Autowired
    private TodoService todoService;

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
           // Add Todo items and save to db
            Todo todo1 = new Todo("Wash the dishes", new Date(), true) ;
            Todo todo2 = new Todo("Learn React Props", new Date(), false) ;
            Todo todo3 = new Todo("Go to the Gym", new Date(), true) ;
            todoService.saveOrUpdate(todo1);
            todoService.saveOrUpdate(todo2);
            todoService.saveOrUpdate(todo3);
        };
    }





}
