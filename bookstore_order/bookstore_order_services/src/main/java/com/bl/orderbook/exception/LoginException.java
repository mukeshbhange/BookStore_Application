package com.bl.orderbook.exception;

public class LoginException extends Exception{
	/**
	 * 
	 */
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
