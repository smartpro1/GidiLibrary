package com.gidilibrary.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateBookPayload {
	@NotBlank(message="please enter book status to update")
	@Size(min = 8, max = 9, message="book status can either be available or borrowed")
	private String bookStatus;

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	
   
   
}
