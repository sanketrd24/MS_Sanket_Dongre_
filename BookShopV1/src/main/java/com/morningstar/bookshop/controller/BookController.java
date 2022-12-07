package com.morningstar.bookshop.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.morningstar.bookshop.entity.Books;
import com.morningstar.bookshop.exception.BookAlreadyExistsException;
import com.morningstar.bookshop.exception.BookNotFoundException;
import com.morningstar.bookshop.repository.BookRepository;
import com.morningstar.bookshop.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/add")
	public @ResponseBody String addBook(@RequestBody Books book) {
		try {
			return bookService.addBook(book);
		} catch (BookAlreadyExistsException e) {

			return e.getMessage();
		}
	}

	@GetMapping("/getAllBooks")
	public List<Books> getAllBooks() {
		return bookService.getAllBooks();
	}

//	@DeleteMapping("/delete/{id}")
//	public void deleteBook(@PathVariable("id") int bookId) {
//		bookService.deleteBookByBookId(bookId);
//	}

	@GetMapping("/getbookbyname/{bookName}")
	public ResponseEntity<Books> getBookByName(@PathVariable("bookName") String bookName) throws BookNotFoundException {

		Books book = null;
		book = bookService.getBookByName(bookName);
		return ResponseEntity.of(Optional.of(book));
	}
}
