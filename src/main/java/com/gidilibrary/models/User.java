package com.gidilibrary.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullname;
	private String userRegNo;
	private LocalDateTime created_At;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BookTransaction> bookTransactions = new ArrayList<>();

	public User(String fullname, String userRegNo, List<BookTransaction> bookTransactions) {
		this.fullname = fullname;
		this.userRegNo = userRegNo;
		this.bookTransactions = bookTransactions;
	}
	
	public User() {
		
	}

	public Long getId() {
		return id;
	}


	public String getFullname() {
		return fullname;
	}

	public String getUserRegNo() {
		return userRegNo;
	}


	public List<BookTransaction> getBookTransactions() {
		return bookTransactions;
	}

	public void setBookTransactions(List<BookTransaction> bookTransactions) {
		this.bookTransactions = bookTransactions;
	}
	
	
	@PrePersist
	protected void onCreate() {
		this.created_At = LocalDateTime.now();
	}
	
}
