package com.gidilibrary.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String author;
	private String edition;
	private String isbn;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfProduction;
	private String status = "available"; 
	
	@Column(updatable = false)
	private LocalDateTime created_At;
	
	private LocalDateTime updated_At;
	
	public Book() {
	
	}

	public Book(String title, String author, String edition, String isbn, LocalDate dateOfProduction) {
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.isbn = isbn;
		this.dateOfProduction = dateOfProduction;
	}
	
	

	public Long getId() {
		return id;
	}


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


	public LocalDate getDateOfProduction() {
		return dateOfProduction;
	}


	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public LocalDateTime getCreated_At() {
		return created_At;
	}


	public LocalDateTime getUpdated_At() {
		return updated_At;
	}

	
	@PrePersist
	protected void onCreate() {
		this.created_At = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = LocalDateTime.now();
	}
}
