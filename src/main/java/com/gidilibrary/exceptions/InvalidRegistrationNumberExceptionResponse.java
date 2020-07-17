package com.gidilibrary.exceptions;

public class InvalidRegistrationNumberExceptionResponse {
   private String userRegError;

	public InvalidRegistrationNumberExceptionResponse(String userRegError) {
	
		this.userRegError = userRegError;
	}

	public String getUserRegError() {
		return userRegError;
	}

	public void setUserRegError(String userRegError) {
		this.userRegError = userRegError;
	}

   
   
    
}
