package com.gidilibrary.services;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gidilibrary.exceptions.BookNotFoundException;
import com.gidilibrary.exceptions.BookStatusException;
import com.gidilibrary.exceptions.InvalidDateFormatException;
import com.gidilibrary.exceptions.InvalidRegistrationNumberException;
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
		verifyDateFormat(addBookPayload.getDateOfProduction());
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
		Long bookId;
		try {
		bookId = Long.parseLong(lendBookPayload.getBookId());
		} catch(Exception ex) {
			throw new InvalidRegistrationNumberException("Book Id can only be digits");
		}
		Book getBook = findBookById(bookId, "lend book");
		if(!getBook.getStatus().equals("available")) {
			throw new BookNotFoundException("Book with id " + bookId + " is not available");
		}
		
		verifyRegNumber(lendBookPayload.getUserRegNo());
		User user = userRepo.getByUserRegNo(lendBookPayload.getUserRegNo());
		if(user == null) {
			throw new UserNotFoundException("Invalid user - this user has not been registered");
		}
		
		getBook.setStatus("borrowed");
		getBook = bookRepo.save(getBook);
		verifyDateFormat(lendBookPayload.getExpectedDateOfReturn());
		LocalDate expectedDateOfReturn = LocalDate.parse(lendBookPayload.getExpectedDateOfReturn());
		LocalDate todaysDate = LocalDate.now();
		if(expectedDateOfReturn.isBefore(todaysDate)) {
			throw new InvalidDateFormatException("expected day of return must not be in the past");
		}
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
		User user = userRepo.getByUserRegNo(registerUserPayload.getUserRegNo());
		if(user != null) {
			throw new UserAlreadyExistException("User with registration number " + registerUserPayload.getUserRegNo() + " already exists");
		}
		
		verifyRegNumber(registerUserPayload.getUserRegNo());
		User registerUser= new User(registerUserPayload.getFullname(),registerUserPayload.getUserRegNo());
		
		User registeredUser = userRepo.save(registerUser);
		return registeredUser;
	}

	private void verifyRegNumber(String userRegNo) {
		if(userRegNo.length() < 3 || !userRegNo.matches("[0-9]+")) {
			throw new InvalidRegistrationNumberException("registration number must be all digits and atleast 3 digits in length");
		}
	}
	
	private void verifyDateFormat(String dateString) {
		if(!dateString.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			throw new InvalidDateFormatException("date should be in yyyy-mm-dd format e.g 2020-07-23");
		}
	}


 
}
