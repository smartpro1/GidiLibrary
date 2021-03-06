package com.gidilibrary.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.gidilibrary.models.Book;
import com.gidilibrary.models.User;
import com.gidilibrary.payloads.AddBookPayload;
import com.gidilibrary.payloads.LendBookPayload;
import com.gidilibrary.payloads.RegisterUserPayload;
import com.gidilibrary.payloads.UpdateBookPayload;
import com.gidilibrary.services.BookService;
import com.gidilibrary.validators.InputValidator;


@RestController
@RequestMapping("")
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

	@GetMapping
	public ResponseEntity<String> health() {
		return new ResponseEntity<>("Welcome to Gidi Library..😊", HttpStatus.OK);
	}

	@PostMapping("/books")
	public ResponseEntity<?> addBook(@Valid @RequestBody AddBookPayload addBookPayload, BindingResult result) {
		
		// validate input fields
		ResponseEntity<?> errorMap = inputValidator.validateFields(result);
		if(errorMap != null) return errorMap;
		Book addedBook = bookService.addBook(addBookPayload);
		
		return new ResponseEntity<Book>(addedBook, HttpStatus.OK);
		
	}
	
	@PostMapping("books/lend-book")
	public ResponseEntity<?> lendBook(@Valid @RequestBody LendBookPayload lendBookPayload, BindingResult result){
		ResponseEntity<?> errorMap = inputValidator.validateFields(result);
		if(errorMap != null) return errorMap;
		bookService.lendBook(lendBookPayload);
		return ResponseEntity.ok("Transaction successful");
	}
	
	
	@PutMapping("books/{bookId}")
	public ResponseEntity<?> updateBookById(@PathVariable String bookId, @Valid @RequestBody UpdateBookPayload updateBookPayload) {
		
		Book updatedBook = bookService.updateBookById(bookId, updateBookPayload.getBookStatus());
		return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
		
	}
	
	 @DeleteMapping("books/{bookId}")
	    public ResponseEntity<String> deleteBookById(@PathVariable String bookId){
		 bookService.deleteBookById(bookId);
		 return new ResponseEntity<String>("Book with id " + bookId + " deleted successfully", HttpStatus.OK);
	 }
	 
	 @GetMapping("books")
	 public ResponseEntity<?> getAllBooks(){
		List<Book> allBooks = bookService.findAll();
		if(allBooks.size() == 0) {
			return ResponseEntity.ok("No books found");
		}
		return new ResponseEntity<List<Book>>(allBooks, HttpStatus.OK);
	 }
	 
	 @GetMapping("books/{bookId}")
	 public ResponseEntity<?> getBookById(@PathVariable String bookId){
			Book book = bookService.findById(bookId);
			if(book == null) {
				return ResponseEntity.ok("Book with id " + bookId + " cannot be found");
			}
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		 }
	 
	 
	 @PostMapping("books/register-user")
	 public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserPayload registerUserPayload, BindingResult result){
		 
			// validate input fields
			ResponseEntity<?> errorMap = inputValidator.validateFields(result);
			if(errorMap != null) return errorMap;
			
		User registeredUser = bookService.registerUser(registerUserPayload);
		return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
	 }
}
