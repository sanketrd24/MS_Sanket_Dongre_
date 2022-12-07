package com.morningstar.bookshop.exception;

public class InvalidLoginCredentialException extends RuntimeException {
	private String message;

	public InvalidLoginCredentialException() {
		super();
	}

	public InvalidLoginCredentialException(String message) {
		super(message);
		this.message = message;
	}
	
}
