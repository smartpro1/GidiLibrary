package com.gidilibrary.exceptions;

public class BookNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -2649718184052781107L;

	public BookNotFoundException(String message) {
		super(message);
	}

	
}
