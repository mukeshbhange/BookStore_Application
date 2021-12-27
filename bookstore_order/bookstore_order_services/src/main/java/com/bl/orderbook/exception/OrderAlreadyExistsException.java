package com.bl.orderbook.exception;


/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : ExceptionHandling
 *
 */
public class OrderAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String message;

	public OrderAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public OrderAlreadyExistsException() {
	}
}

