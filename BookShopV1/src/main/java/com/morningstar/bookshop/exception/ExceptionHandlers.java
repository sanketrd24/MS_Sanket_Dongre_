package com.morningstar.bookshop.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

	@Value(value="${data.exception.message0}")
	private String message0;
	
	@Value(value = "${data.exception.message1}")
	private String message1;
	
	@Value(value="${data.exception.message2}")
	private String message2;
	
	@Value(value="${data.exception.message3}")
	private String message3;
	
	@ExceptionHandler(value=BookNotFoundException.class)
	public ResponseEntity<String> bookNotFoundException(BookNotFoundException bookNotFoundException){
		return new ResponseEntity<String>(message1,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=BookAlreadyExistsException.class)
	public ResponseEntity<String> bookAlreadyExistException(BookAlreadyExistsException alreadyExistsException){
		return new ResponseEntity<String>(message2,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=InvalidLoginCredentialException.class)
	public ResponseEntity<String> invalidLoginCredential(InvalidLoginCredentialException invalidLoginCredentialException){
		return new ResponseEntity<String>(message3,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<String> handleException(Exception exception){
		return new ResponseEntity<>(message0,HttpStatus.BAD_REQUEST);
	}
}
