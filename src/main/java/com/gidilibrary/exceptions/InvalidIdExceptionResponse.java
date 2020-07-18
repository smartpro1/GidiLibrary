package com.gidilibrary.exceptions;

public class InvalidIdExceptionResponse {
   private String bookIdError;

	public InvalidIdExceptionResponse(String bookIdError) {
	
		this.bookIdError = bookIdError;
	}

	public String getBookIdError() {
		return bookIdError;
	}

	public void setBookIdError(String bookIdError) {
		this.bookIdError = bookIdError;
	}

       
    
}
