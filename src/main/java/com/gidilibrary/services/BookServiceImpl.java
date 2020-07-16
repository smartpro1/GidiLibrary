package com.gidilibrary.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gidilibrary.exceptions.BookNotFoundException;
import com.gidilibrary.exceptions.BookStatusException;
import com.gidilibrary.models.Book;
import com.gidilibrary.payloads.AddBookPayload;
import com.gidilibrary.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository bookRepo;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}

	@Override
	public Book addBook(AddBookPayload addBookPayload) {
		LocalDate dateOfProduction = LocalDate.parse(addBookPayload.getDateOfProduction());
		
		Book newBook = new Book(addBookPayload.getTitle(), addBookPayload.getAuthor(), addBookPayload.getEdition(),
				addBookPayload.getIsbn(), dateOfProduction);
		
		Book savedBook = bookRepo.save(newBook);
		return savedBook;
	}

	@Override
	public Book updateBookById(long bookId, String bookStatus) {
		Book getBook  = findBookById(bookId, "Update");
		if(!bookStatus.equalsIgnoreCase("available") && !bookStatus.equalsIgnoreCase("borrowed")) {
			throw new BookStatusException("update declined - book status can either be available or borrowed and not " + bookStatus);
		}
		
		if(getBook.getStatus().equalsIgnoreCase(bookStatus)) {
			throw new BookStatusException("update declined - book already " + bookStatus);
		}
		
		getBook.setStatus(bookStatus.toLowerCase());
		
		return bookRepo.save(getBook);
	}

	@Override
	public void deleteBookById(long bookId) {
		findBookById(bookId, "Delete");
		bookRepo.deleteById(bookId);
		
	}
	
	public Book findBookById(long bookId, String action) {
		Book foundBook = bookRepo.getById(bookId);
		
		if(foundBook == null) {
			throw new BookNotFoundException( action + " declined - book with id " + bookId + " cannot be found.");
		}
		
		return foundBook;
	}


 
}
