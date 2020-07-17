package com.gidilibrary.exceptions;

public class UserAlreadyExistExceptionResponse {
   private String userError;

	public UserAlreadyExistExceptionResponse(String userError) {
	
		this.userError = userError;
	}

	public String getUserError() {
		return userError;
	}

	public void setUserError(String userError) {
		this.userError = userError;
	}
   
    
}
