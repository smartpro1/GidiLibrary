package com.gidilibrary.exceptions;

public class UserAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = -7084734931655997862L;

    public UserAlreadyExistException(String message) {
		super(message);
	}

	
}
