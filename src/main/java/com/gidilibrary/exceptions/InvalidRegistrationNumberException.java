package com.gidilibrary.exceptions;

public class InvalidRegistrationNumberException extends RuntimeException{
	private static final long serialVersionUID = -1086824802652884205L;

	public InvalidRegistrationNumberException(String message) {
		super(message);
	}

	
}
