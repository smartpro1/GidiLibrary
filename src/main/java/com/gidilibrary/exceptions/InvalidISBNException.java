package com.gidilibrary.exceptions;

public class InvalidISBNException extends RuntimeException{
    

	private static final long serialVersionUID = -5871964055214574389L;

	public InvalidISBNException(String message) {
		super(message);
	}

	
}
