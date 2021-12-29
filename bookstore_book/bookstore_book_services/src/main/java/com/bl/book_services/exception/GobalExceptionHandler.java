package com.bl.book_services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bl.book_services.response.Response;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import lombok.extern.slf4j.Slf4j;
/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : GlobalExceptionHandling
 *
 */

@Slf4j
@ControllerAdvice
public class GobalExceptionHandler{
	
	
	private static final String message ="Exception while processing REST request";

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Response> handleMessageNotReadableException(HttpMessageNotReadableException exception){
		
		log.error("Invalid date Format.",exception);
		Response responsedto = new Response(message,null, "Date should be in dd MMM yyyy format");
		return new ResponseEntity<Response>(responsedto,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
		List<ObjectError> errorList =exception.getBindingResult().getAllErrors();
		List<String> errMsg =errorList.stream().map(objErr->objErr.getDefaultMessage()).collect(Collectors.toList());
		Response responseDTO = new Response("Exception while processing REST request",null, errMsg);
		return new ResponseEntity<Response>(responseDTO,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Response> handleEmployeeNotFoundException(NoSuchElementException exception){
		Response responsedto = new Response("Eception While Proceesg RestRequest",null, exception.getMessage());
		return new ResponseEntity<Response>(responsedto,HttpStatus.BAD_REQUEST);	
	}

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
