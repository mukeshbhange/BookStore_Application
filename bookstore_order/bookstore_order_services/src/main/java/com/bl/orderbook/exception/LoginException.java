package com.bl.orderbook.exception;

/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : ExceptionHandling
 *
 */
public class LoginException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message;

	public LoginException(String message) {
		super(message);
		this.message = message;
	}

	public LoginException() {
	}

	public String getMessage() {
		return message;
	}

}
