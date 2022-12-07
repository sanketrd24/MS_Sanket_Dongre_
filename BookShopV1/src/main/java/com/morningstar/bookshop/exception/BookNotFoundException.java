package com.morningstar.bookshop.exception;

public class BookNotFoundException extends RuntimeException{
	
	private String message;

	public BookNotFoundException() {
		super();
	}

	public BookNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
	
}
