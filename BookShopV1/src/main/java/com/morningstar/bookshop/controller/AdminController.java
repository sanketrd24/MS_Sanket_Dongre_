package com.morningstar.bookshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.morningstar.bookshop.entity.Books;
import com.morningstar.bookshop.entity.Orders;
import com.morningstar.bookshop.entity.Users;
import com.morningstar.bookshop.exception.BookAlreadyExistsException;
import com.morningstar.bookshop.exception.BookNotFoundException;
import com.morningstar.bookshop.service.BookService;
import com.morningstar.bookshop.service.OrderService;
import com.morningstar.bookshop.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;

	@Autowired
	BookService bookService;

	// Logger
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	public ResponseEntity<Object> adminLogin(@RequestBody Users user) {
		String email = user.getEmail();
		String password = user.getPassword();
		String messege = userService.loginByEmailandPassword(email, password);
		return new ResponseEntity<>(messege, HttpStatus.OK);
	}

	// methods related to books

	// Add Book Method
	@PostMapping("/addbook")
	public String addBook(@Valid @RequestBody Books book) throws BookAlreadyExistsException {

		logger.info("Add Book : " + "Book id : " + book.getBookId() + " ,book name : " + book.getBookName());
		return bookService.addBook(book);

	}

	// Get All Books
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Books>> getAllBooks() {
		return new ResponseEntity<List<Books>>(bookService.getAllBooks(), HttpStatus.OK);
	}

	// Delete Book By Name
	@DeleteMapping("/delete/{bookName}")
	public void deleteBook(@PathVariable("bookName") String bookName) throws BookNotFoundException {
		logger.info("Deleting Book : " + bookName);
		bookService.deleteBookByBookId(bookName);
	}

	// Get book by name
	@GetMapping("/getbookbyname/{bookName}")
	public ResponseEntity<Books> getBookByName(@PathVariable("bookName") String bookName) throws BookNotFoundException {

		Books book = null;
		book = bookService.getBookByName(bookName);
		logger.info("Get Book : " + "Book id : " + book.getBookId() + " ,book name : " + book.getBookName());
		return new ResponseEntity<Books>(book, HttpStatus.FOUND);
	}

	// update book by book name
	@PutMapping("/updatebook")
	public ResponseEntity<Books> updateBook(@RequestBody Books book) throws BookNotFoundException {
		logger.info("Update Book : " + "Book id : " + book.getBookId() + " ,book name : " + book.getBookName());
		return new ResponseEntity<Books>(bookService.updateBook(book), HttpStatus.OK);
	}

	// methods related to Orders
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<Orders>> getAllOrders() {
		return new ResponseEntity<List<Orders>>(orderService.getAllOrders(), HttpStatus.OK);
	}

	// Methods related to Users
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<Users>> getAllUsers() {
		return new ResponseEntity<List<Users>>(userService.getAllUsers(), HttpStatus.OK);
	}

	// validation message handling
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return errors;
	}
}
