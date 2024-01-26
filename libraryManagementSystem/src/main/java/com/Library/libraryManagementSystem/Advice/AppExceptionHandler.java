package com.Library.libraryManagementSystem.Advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }
}

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NullPointerException.class)
//    public Map<String,String> handleNotFound(NullPointerException ex){
//        Map<String,String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error->{
//            errors.put(error.getField(),error.getDefaultMessage());
//        });
//        return errors;
//    }

