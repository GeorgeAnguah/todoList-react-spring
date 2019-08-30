package com.developerboard.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class Description
 *
 * @author Nana on 8/17/2019
 * @version 1.0
 * @see
 * @since 1.0
 */

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class TodoResourceUnavailableException extends RuntimeException{

    public TodoResourceUnavailableException() {
    }

    public TodoResourceUnavailableException(String message) {
        super(message);
    }
}
