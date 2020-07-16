package com.gidilibrary.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LendBookPayload {

	@NotBlank(message="please enter fullname of borrower")
	@Size(min = 6, max = 60, message="fullname must be between 6 - 60 characters long")
	private String fullnameOfBorrower;
	@NotBlank(message="please enter registration number of borrower/user")
	@Size(min = 3, max = 60, message="registration number must be atleast three digits e.g 004")
	private String userRegNo;
	@NotBlank(message="please enter valid book id")
	private Long bookId;
	@NotBlank(message="please enter expected date of return in the format yyyy-mm-dd")
	@Size(min = 10, max = 10, message="date must be exactly ten characters e.g 2020-07-25")
	private String expectedDateOfReturn;
	@NotBlank(message="please enter your name, the one giving out the book")
	@Size(min = 6,  message="name must be more than 5 characters")
	private String nameOfStaffOnDuty;
	
	public String getFullnameOfBorrower() {
		return fullnameOfBorrower;
	}
	public String getUserRegNo() {
		return userRegNo;
	}
	public Long getBookId() {
		return bookId;
	}
	public String getExpectedDateOfReturn() {
		return expectedDateOfReturn;
	}
	public String getNameOfStaffOnDuty() {
		return nameOfStaffOnDuty;
	}
	
	
}
