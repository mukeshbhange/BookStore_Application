package com.bl.cart_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GobalExceptionHandler{

	@ExceptionHandler(value = CartNotFoundException.class)
	public ResponseEntity<String> cartNotFoundException(CartNotFoundException cartNotFoundException) {
		return new ResponseEntity<String>(cartNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CartAlreadyExistsException.class)
	public ResponseEntity<String> cartAlreadyExistsException(CartAlreadyExistsException cartAlreadyExistsException) {
		return new ResponseEntity<String>("Cart already exists", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = LoginException.class)
	public ResponseEntity<String> loginException(LoginException loginException) {
		return new ResponseEntity<String>(loginException.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
		return new ResponseEntity<>("DB Connection Lost", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
