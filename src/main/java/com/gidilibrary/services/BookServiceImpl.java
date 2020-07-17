package com.gidilibrary.services;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gidilibrary.exceptions.BookNotFoundException;
import com.gidilibrary.exceptions.BookStatusException;
import com.gidilibrary.exceptions.UserAlreadyExistException;
import com.gidilibrary.exceptions.UserNotFoundException;
import com.gidilibrary.models.Book;
import com.gidilibrary.models.BookTransaction;
import com.gidilibrary.models.User;
import com.gidilibrary.payloads.AddBookPayload;
import com.gidilibrary.payloads.LendBookPayload;
import com.gidilibrary.payloads.RegisterUserPayload;
import com.gidilibrary.repositories.BookRepository;
import com.gidilibrary.repositories.BookTransactionRepository;
import com.gidilibrary.repositories.UserRepository;

@Service
public class BookServiceImpl implements BookService {
	
	private  BookRepository bookRepo;
	private  UserRepository userRepo;
	private  BookTransactionRepository bookTransactionRepo;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepo, UserRepository userRepo, BookTransactionRepository bookTransactionRepo) {
		this.bookRepo = bookRepo;
		this.userRepo = userRepo;
		this.bookTransactionRepo = bookTransactionRepo;
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
	
	@Override
	public void lendBook(LendBookPayload lendBookPayload) {
		Book getBook = findBookById(lendBookPayload.getBookId(), "lend book");
		if(!getBook.getStatus().equals("available")) {
			throw new BookNotFoundException("Book with id " + lendBookPayload.getBookId() + "is not available");
		}
		
		User user = userRepo.getByRegNo(lendBookPayload.getUserRegNo());
		if(user == null) {
			throw new UserNotFoundException("Invalid user - this user has not been registered");
		}
		
		getBook.setStatus("borrowed");
		getBook = bookRepo.save(getBook);
		LocalDate expectedDateOfReturn = LocalDate.parse(lendBookPayload.getExpectedDateOfReturn());
		BookTransaction newBookTransaction = new BookTransaction(expectedDateOfReturn, getBook,lendBookPayload.getUserRegNo() , 
				          lendBookPayload.getNameOfStaffOnDuty());
		
		newBookTransaction = bookTransactionRepo.save(newBookTransaction);
		List<BookTransaction> userBookTransactions = user.getBookTransactions();
		userBookTransactions.add(newBookTransaction);
		
		userRepo.save(user);
		
	}
	
	public Book findBookById(long bookId, String action) {
		Book foundBook = bookRepo.getById(bookId);
		
		if(foundBook == null) {
			throw new BookNotFoundException( action + " declined - book with id " + bookId + " cannot be found.");
		}
		
		return foundBook;
	}

	@Override
	public List<Book> findAll() {
		List<Book> allBooks = bookRepo.findAll();
		return allBooks;
	}

	@Override
	public Book findById(long bookId) {
		Book book = bookRepo.getById(bookId);
		return book;
	}

	@Override
	public User registerUser(@Valid RegisterUserPayload registerUserPayload) {
		
		// check if user already exists
		User user = userRepo.getByRegNo(registerUserPayload.getUserRegNo());
		if(user != null) {
			throw new UserAlreadyExistException("User with registration number " + registerUserPayload.getUserRegNo() + "already exists");
		}
		
		User registerUser= new User(registerUserPayload.getFullname(),registerUserPayload.getUserRegNo());
		userRepo.save(registerUser);
		return registerUser;
	}

	


 
}
