package com.gidilibrary;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gidilibrary.controller.BookController;
import com.gidilibrary.models.Book;
import com.gidilibrary.models.User;
import com.gidilibrary.payloads.AddBookPayload;
import com.gidilibrary.payloads.LendBookPayload;
import com.gidilibrary.payloads.RegisterUserPayload;
import com.gidilibrary.payloads.UpdateBookPayload;
import com.gidilibrary.services.BookService;
import com.gidilibrary.validators.InputValidator;

//@SpringBootTest
@WebMvcTest(BookController.class)
class GidiLibraryApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper = new ObjectMapper();
	
	@MockBean
	private BookService bookService;
	
	@MockBean
    private BindingResult bindingResult;
	
	@MockBean
	private InputValidator inputValidator;
	
	@Test
	public void addBookShouldReturnBookAndStatusOk() throws Exception{
		LocalDate dateOfProduction = LocalDate.parse("1990-02-22");
		Book newBook = new Book("rich dad and poor dad", "Donald Trump", "first edition","1223-444-667-98", dateOfProduction);
		AddBookPayload addBookPayload = new AddBookPayload();
		
		when(bindingResult.hasErrors()).thenReturn(true);
		when(bookService.addBook(addBookPayload)).thenReturn(newBook);
		 mockMvc.perform(post("/api/v1/books")
		   .contentType("application/json")
		    .content(objectMapper.writeValueAsString(newBook)))
             .andExpect(status().isOk());
				
	}
	

		
	@Test
	public void updateBookShouldReturnBookAndOk() throws JsonProcessingException, Exception {
		LocalDate dateOfProduction = LocalDate.parse("1990-02-22");
		Book updatedBook = new Book("rich dad and poor dad", "Donald Trump", "first edition","1223-444-667-98", dateOfProduction);
		UpdateBookPayload updateBookPayload = new UpdateBookPayload();
		updateBookPayload.setBookStatus("available");
		
		when(bindingResult.hasErrors()).thenReturn(true);
		when(bookService.updateBookById(1l, "available")).thenReturn(updatedBook);
		mockMvc.perform(put("/api/v1/books/{bookId}", 1L)
				.contentType("application/json")
				 .content(objectMapper.writeValueAsString(updatedBook)))
                  .andExpect(status().isOk());
	} 
	
	
	@Test
	public void lendBookShouldReturnBookAndStatusOk() throws Exception{
		LocalDate dateOfProduction = LocalDate.parse("1990-02-22");
		Book newBook = new Book("rich dad and poor dad", "Donald Trump", "first edition","1223-444-667-98", dateOfProduction);
		AddBookPayload addBookPayload = new AddBookPayload();
		
		when(bindingResult.hasErrors()).thenReturn(true);
		when(bookService.addBook(addBookPayload)).thenReturn(newBook);
		 mockMvc.perform(post("/api/v1/books")
		   .contentType("application/json")
		    .content(objectMapper.writeValueAsString(newBook)))
             .andExpect(status().isOk());
				
	}
	
	@Test
	public void registerUserShouldReturnUserAndIsCreatedStatus() throws Exception{
		User user = new User("Mark Zuckerberg", "001");
		RegisterUserPayload registerUserPayload = new RegisterUserPayload();
		
		when(bindingResult.hasErrors()).thenReturn(true);
		when(bookService.registerUser(registerUserPayload)).thenReturn(user);
		 mockMvc.perform(post("/api/v1/books")
		   .contentType("application/json")
		    .content(objectMapper.writeValueAsString(user)))
             .andExpect(status().isOk());
				
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
