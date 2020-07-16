package com.gidilibrary.exceptions;

public class BookStatusExceptionResponse {
   private String bookUpdateError;

	public BookStatusExceptionResponse(String bookUpdateError) {
	
		this.bookUpdateError = bookUpdateError;
	}

	public String getBookUpdateError() {
		return bookUpdateError;
	}

	public void setBookUpdateError(String bookUpdateError) {
		this.bookUpdateError = bookUpdateError;
	}

	
    
}
