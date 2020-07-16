package com.gidilibrary.exceptions;

public class UserNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1799582139173570424L;

	public UserNotFoundException(String message) {
		super(message);
	}

	
}
