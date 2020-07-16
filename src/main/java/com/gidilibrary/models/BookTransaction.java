package com.gidilibrary.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class BookTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	LocalDateTime dateTimeBorrowed;
	private LocalDate expectedDateOfReturn;
	LocalDateTime dateReturned;
	private String regNoOfBorrower;
	private String staffOnDuty;
	
	@OneToOne
	@JoinColumn(name="user_id", nullable=false)
	private Book book;

	public BookTransaction(LocalDate expectedDateOfReturn, Book book, String regNoOfBorrower, String staffOnDuty) {
		this.expectedDateOfReturn = expectedDateOfReturn;
		this.book = book;
		this.regNoOfBorrower = regNoOfBorrower;
		this.staffOnDuty = staffOnDuty;
	}

	public BookTransaction() {
		
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDateTimeBorrowed() {
		return dateTimeBorrowed;
	}

	public LocalDate getExpectedDateOfReturn() {
		return expectedDateOfReturn;
	}

	public LocalDateTime getDateReturned() {
		return dateReturned;
	}

	public Book getBook() {
		return book;
	}
	
	
	
	public String getRegNoOfBorrower() {
		return regNoOfBorrower;
	}

	public String getStaffOnDuty() {
		return staffOnDuty;
	}

	@PrePersist
	protected void onCreate() {
		this.dateTimeBorrowed = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.dateReturned = LocalDateTime.now();
	}
	
	
	
}
