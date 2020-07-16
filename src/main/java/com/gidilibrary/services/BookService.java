package com.gidilibrary.services;




import javax.validation.Valid;

import com.gidilibrary.models.Book;
import com.gidilibrary.payloads.AddBookPayload;
import com.gidilibrary.payloads.LendBookPayload;


public interface BookService {
	Book addBook(AddBookPayload addBookPayload);

	void deleteBookById(long bookId);

	Book updateBookById(long bookId, String bookStatus);

	void lendBook(@Valid LendBookPayload lendBookPayload);
}
