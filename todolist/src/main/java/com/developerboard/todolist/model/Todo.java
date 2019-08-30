package com.developerboard.todolist.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Class Description
 *
 * @author Nana on 7/31/2019
 * @version 1.0
 * @see
 * @since 1.0
 */

@Data
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "todo name cannot be empty")
    private String name;
    private Date dueDate;
    private boolean completed;

    public Todo(){
    }

    public Todo(String name, Date dueDate, boolean completed) {
        this.name = name;
        this.dueDate = dueDate;
        this.completed = completed;
    }
}
