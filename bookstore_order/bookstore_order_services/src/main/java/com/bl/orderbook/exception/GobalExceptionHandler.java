package com.bl.orderbook.exception;

/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : GlobalExceptionHandling
 *
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GobalExceptionHandler{

	@ExceptionHandler(value = OrderNotFoundException.class)
	public ResponseEntity<String> orderNotFoundException(OrderNotFoundException orderNotFoundException) {
		return new ResponseEntity<String>(orderNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = OrderAlreadyExistsException.class)
	public ResponseEntity<String> orderAlreadyExistsException(OrderAlreadyExistsException orderAlreadyExistsException) {
		return new ResponseEntity<String>("Book already exists", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = LoginException.class)
	public ResponseEntity<String> loginException(LoginException loginException) {
		return new ResponseEntity<String>(loginException.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
		return new ResponseEntity<>("DB Connection Lost", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
