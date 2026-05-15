package com.LaxmiPriya.StudentManagement.Exceptions;

import java.util.Map;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)

	public ResponseEntity<Map<String, String>> handle(StudentNotFoundException ex) {

		Map<String, String> error = new HashMap<>();

		error.put("message", ex.getMessage());

		return ResponseEntity.status(404).body(error);
	}

	@ExceptionHandler(EmailAlreadyExistsException.class)

	public ResponseEntity<Map<String, String>> handle(EmailAlreadyExistsException ex) {

		Map<String, String> error = new HashMap<>();

		error.put("message", ex.getMessage());

		return ResponseEntity.status(409).body(error);
	}
	
	@ExceptionHandler(CourseNotFoundException.class)

	public ResponseEntity<Map<String, String>> handle(CourseNotFoundException ex) {

		Map<String, String> error = new HashMap<>();

		error.put("message", ex.getMessage());

		return ResponseEntity.status(404).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {

			errors.put(error.getField(), error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(UserNotFoundException.class)

	public ResponseEntity<Map<String, String>> handle(UserNotFoundException ex) {

		Map<String, String> error = new HashMap<>();

		error.put("message", ex.getMessage());

		return ResponseEntity.status(404).body(error);
	}
	
	
	  @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handle(Exception ex) {

	        ErrorResponse error = ErrorResponse.builder()
	                .message("Unknown Exception occured")
	               
	                .build();

	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}

/*
 * @ExceptionHandler(InvalidStudentDataException.class)
 * 
 * public ResponseEntity<Map<String,String>> handle(InvalidStudentDataException
 * ex) {
 * 
 * Map<String,String> error = new HashMap<>();
 * 
 * error.put("message", ex.getMessage());
 * 
 * return ResponseEntity.status(404) .body(error); }
 * 
 * @ExceptionHandler( ConstraintViolationException.class)
 * 
 * public ResponseEntity<Map<String,String>> handle(
 * ConstraintViolationException ex){
 * 
 * Map<String,String> errors = new HashMap<>();
 * 
 * ex.getConstraintViolations() .forEach(error -> {
 * 
 * String field = error.getPropertyPath().toString();
 * 
 * String message = error.getMessage();
 * 
 * errors.put(field, message); });
 * 
 * return ResponseEntity .badRequest() .body(errors); }
 */
