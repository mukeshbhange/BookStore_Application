package com.bl.book_services.exception;

public class BookNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public BookNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public BookNotFoundException() {
	}

	public String getMessage() {
		return message;
	}

}