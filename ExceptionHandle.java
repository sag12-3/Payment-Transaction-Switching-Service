package com.sagar.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle  {

	 @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<?> handleInvalidRequest(IllegalArgumentException ex) {
	        Map<String, Object> error = new HashMap<>();
	        error.put("timestamp", LocalDateTime.now());
	        error.put("status", HttpStatus.BAD_REQUEST.value());
	        error.put("error", "Bad Request");
	        error.put("message", ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

   
}
