package com.bl.book_services.exception;

/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : Exception Handling
 *
 */
public class BookAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String message;

	public BookAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public BookAlreadyExistsException() {
	}
}

