package com.bl.registration.exception;


/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 *
 */
public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String message;

	public UserAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public UserAlreadyExistsException() {
	}
}

