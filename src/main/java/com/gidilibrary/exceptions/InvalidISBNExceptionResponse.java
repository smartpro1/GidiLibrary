package com.gidilibrary.exceptions;

public class InvalidISBNExceptionResponse {
   private String isbnError;

	public InvalidISBNExceptionResponse(String isbnError) {
	
		this.isbnError = isbnError;
	}

	public String getIsbnError() {
		return isbnError;
	}

	public void setIsbnError(String isbnError) {
		this.isbnError = isbnError;
	}


    
       
    
}
