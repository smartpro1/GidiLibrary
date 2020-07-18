package com.gidilibrary.services;




import java.util.List;

import javax.validation.Valid;

import com.gidilibrary.models.Book;
import com.gidilibrary.models.User;
import com.gidilibrary.payloads.AddBookPayload;
import com.gidilibrary.payloads.LendBookPayload;
import com.gidilibrary.payloads.RegisterUserPayload;


public interface BookService {
	Book addBook(AddBookPayload addBookPayload);

	void deleteBookById(String bookId);

	Book updateBookById(String bookId, String bookStatus);

	void lendBook(@Valid LendBookPayload lendBookPayload);

	List<Book> findAll();

	Book findById(String bookId);

	User registerUser(@Valid RegisterUserPayload registerUserPayload);
}
