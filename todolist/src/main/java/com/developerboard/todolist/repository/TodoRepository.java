package com.developerboard.todolist.repository;

import com.developerboard.todolist.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Class Description
 *
 * @author Nana on 7/31/2019
 * @version 1.0
 * @see
 * @since 1.0
 */

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    Todo getById(Long id);
}

