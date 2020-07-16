package com.gidilibrary.payloads;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddBookPayload {
	@NotBlank(message="please enter title of book")
	@Size(min = 6, max = 60, message="book title must be between 6 - 60 characters long")
	private String title;
	@NotBlank(message="please enter name of author")
	@Size(min = 6, max = 40, message="name of author must be between 6 - 40 characters long")
	private String author;
	@NotBlank(message="please enter edition e.g first edition")
	@Size(min = 10, max = 30, message="edition must be between 10 - 30 characters long")
	private String edition;
	private String isbn;
	@NotBlank(message="please enter date of production using this format: yyyy-mm-dd")
	@Size(min = 10, max = 10, message="invalid date format, use: yyyy-mm-dd")
	private String dateOfProduction;
	
	
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getEdition() {
		return edition;
	}
	public String getIsbn() {
		return isbn;
	}
	public String getDateOfProduction() {
		return dateOfProduction;
	}
	
	
}
