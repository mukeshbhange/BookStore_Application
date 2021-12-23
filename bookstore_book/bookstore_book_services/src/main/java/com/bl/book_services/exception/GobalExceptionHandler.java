package com.bl.book_services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GobalExceptionHandler{

	@ExceptionHandler(value = BookNotFoundException.class)
	public ResponseEntity<String> bookNotFoundException(BookNotFoundException bookNotFoundException) {
		return new ResponseEntity<String>(bookNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = BookAlreadyExistsException.class)
	public ResponseEntity<String> bookAlreadyExistsException(BookAlreadyExistsException bookAlreadyExistsException) {
		return new ResponseEntity<String>("Book already exists", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = LoginException.class)
	public ResponseEntity<String> BookAlreadyExistsException(LoginException loginException) {
		return new ResponseEntity<String>(loginException.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
		return new ResponseEntity<>("DB Connection Lost", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
