package com.gidilibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex, WebRequest req){
		BookNotFoundExceptionResponse exceptionResponse = new BookNotFoundExceptionResponse(ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleBookStatusException(BookStatusException ex, WebRequest req){
		BookStatusExceptionResponse exceptionResponse = new BookStatusExceptionResponse(ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest req){
		UserNotFoundExceptionResponse exceptionResponse = new UserNotFoundExceptionResponse(ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest req){
		UserAlreadyExistExceptionResponse exceptionResponse = new UserAlreadyExistExceptionResponse(ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleInvalidRegistrationNumberException(InvalidRegistrationNumberException ex, WebRequest req){
		InvalidRegistrationNumberExceptionResponse exceptionResponse = new InvalidRegistrationNumberExceptionResponse(ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleInvalidDateFormatException(InvalidDateFormatException ex, WebRequest req){
		InvalidDateFormatExceptionResponse exceptionResponse = new InvalidDateFormatExceptionResponse(ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleInvalidIdException(InvalidIdException ex, WebRequest req){
		InvalidIdExceptionResponse exceptionResponse = new InvalidIdExceptionResponse(ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleInvalidISBNException(InvalidISBNException ex, WebRequest req){
		InvalidISBNExceptionResponse exceptionResponse = new InvalidISBNExceptionResponse(ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
