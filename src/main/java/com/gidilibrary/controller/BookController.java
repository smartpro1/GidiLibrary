package com.gidilibrary.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.gidilibrary.models.Book;
import com.gidilibrary.payloads.AddBookPayload;
import com.gidilibrary.payloads.UpdateBookPayload;
import com.gidilibrary.services.BookService;
import com.gidilibrary.validators.InputValidator;


@RestController
@RequestMapping("/api/v1/gidilibrary")
@CrossOrigin
public class BookController {
	
	private  BookService bookService;
	private  InputValidator inputValidator;
	
	@Autowired
	public BookController(BookService bookService, InputValidator inputValidator) {
		this.bookService = bookService;
		this.inputValidator = inputValidator;
	}
	
	BookController(){
		
	}
	
    @GetMapping("/")
    public String Test() {
    	return "Welcome home";
    }

	@PostMapping("/add-book")
	public ResponseEntity<?> addBook(@Valid @RequestBody AddBookPayload addBookPayload, BindingResult result) {
		
		// validate input fields
		ResponseEntity<?> errorMap = inputValidator.validateFields(result);
		if(errorMap != null) return errorMap;
		
		Book addedBook = bookService.addBook(addBookPayload);
		
		return new ResponseEntity<Book>(addedBook, HttpStatus.OK);
		
	}
	
	
	@PutMapping("/{bookId}")
	public ResponseEntity<?> updateBookById(@PathVariable long bookId, @Valid @RequestBody UpdateBookPayload updateBookPayload, 
            BindingResult result) {
		
		Book addedBook = bookService.updateBookById(bookId, updateBookPayload.getBookStatus());
		
		return new ResponseEntity<Book>(addedBook, HttpStatus.OK);
		
	}
	
	 @DeleteMapping(value="/{bookId}")
	    public ResponseEntity<String> deleteBookById(@PathVariable long bookId){
		 bookService.deleteBookById(bookId);
		 return ResponseEntity.ok("Book with id " + bookId + " deleted successfully");
	 }
}
