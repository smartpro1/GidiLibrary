package com.gidilibrary.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterUserPayload {

	@NotBlank(message="please enter fullname")
	@Size(min = 6, max = 60, message="fullname must be between 6 - 60 characters long")
	private String fullname;
	@NotBlank(message="please enter user Registration Number e.g 001")
	@Size(min = 3, message="user registration number must be atleast 3 digits e.g 022")
	private String userRegNo;
	
	public String getFullname() {
		return fullname;
	}
	public String getUserRegNo() {
		return userRegNo;
	}
	
	
}
