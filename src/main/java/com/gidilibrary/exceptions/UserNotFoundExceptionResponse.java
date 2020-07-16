package com.gidilibrary.exceptions;

public class UserNotFoundExceptionResponse {
   private String userError;

	public UserNotFoundExceptionResponse(String userError) {
	
		this.userError = userError;
	}

	public String getUserError() {
		return userError;
	}

	public void setUserError(String userError) {
		this.userError = userError;
	}

	
   
    
}
