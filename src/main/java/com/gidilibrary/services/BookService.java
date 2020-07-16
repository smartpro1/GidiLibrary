package com.gidilibrary.services;




import com.gidilibrary.models.Book;
import com.gidilibrary.payloads.AddBookPayload;


public interface BookService {
	Book addBook(AddBookPayload addBookPayload);

	void deleteBookById(long bookId);

	Book updateBookById(long bookId, String bookStatus);
}
