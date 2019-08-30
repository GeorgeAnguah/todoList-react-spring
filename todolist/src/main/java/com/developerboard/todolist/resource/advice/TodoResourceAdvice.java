package com.developerboard.todolist.resource.advice;

import com.developerboard.todolist.exception.TodoResourceNotCreatedException;
import com.developerboard.todolist.exception.TodoResourceUnavailableException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class Description
 *
 * @author Nana on 8/18/2019
 * @version 1.0
 * @since 1.0
 */
@ControllerAdvice
public class TodoResourceAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {TodoResourceUnavailableException.class})
    public ResponseEntity<?> handleNotFound(RuntimeException ex, WebRequest req) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, req);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {TodoResourceNotCreatedException.class})
    public ResponseEntity<?> handleBadRequest(RuntimeException ex, WebRequest req) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
    }
}
