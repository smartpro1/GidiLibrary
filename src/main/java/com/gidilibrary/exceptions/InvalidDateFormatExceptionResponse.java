package com.gidilibrary.exceptions;

public class InvalidDateFormatExceptionResponse {
   private String dateFormatError;

	public InvalidDateFormatExceptionResponse(String dateFormatError) {
	
		this.dateFormatError = dateFormatError;
	}

	public String getDateFormatError() {
		return dateFormatError;
	}

	public void setDateFormatError(String dateFormatError) {
		this.dateFormatError = dateFormatError;
	}

	
}
