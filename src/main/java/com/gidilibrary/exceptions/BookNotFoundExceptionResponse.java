package com.gidilibrary.exceptions;

public class BookNotFoundExceptionResponse {
   private String bookError;

	public BookNotFoundExceptionResponse(String bookError) {
	
		this.bookError = bookError;
	}

	public String getBookError() {
		return bookError;
	}

	public void setBookError(String bookError) {
		this.bookError = bookError;
	}
   
    
}
