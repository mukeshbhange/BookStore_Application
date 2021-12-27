package com.bl.orderbook.exception;


/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : ExceptionHandling
 *
 */
public class OrderNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	public OrderNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public OrderNotFoundException() {
	}

	public String getMessage() {
		return message;
	}

}
