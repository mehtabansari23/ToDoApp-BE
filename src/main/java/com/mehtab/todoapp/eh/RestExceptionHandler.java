package com.mehtab.todoapp.eh;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler 
  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleIllegalArgumentException(Exception ex, WebRequest request){
        return ResponseEntity.badRequest().build();
    }
    
    @ExceptionHandler({ ConstraintViolationException.class, SQLIntegrityConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request){
    	 return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
}