package com.morningstar.bookshop.service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morningstar.bookshop.entity.Books;
import com.morningstar.bookshop.exception.BookAlreadyExistsException;
import com.morningstar.bookshop.exception.BookNotFoundException;
import com.morningstar.bookshop.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public String addBook(Books book) throws BookAlreadyExistsException {

		Optional<Books> existingbook = bookRepository.findByName(book.getBookName());
		if (existingbook.isPresent()) {
			throw new BookAlreadyExistsException();
		} else {
			bookRepository.save(book);
			return "book added";
		}

	}

	@Override
	public List<Books> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public void deleteBookByBookId(String bookName) throws BookNotFoundException {
		Optional<Books> book = bookRepository.findByName(bookName);
		if (book == null) {
			throw new BookNotFoundException();
		} else
			bookRepository.deleteById(book.get().getBookId());

	}

//	@Override
//	public void updateBookByBookId(int bookId) {
//		bookRepository.
//
//	}

//	@Override
//	public Optional<Books> getBookByBookId(int bookId) throws BookNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Books> OBook = bookRepository.findById(bookId);
//		if(OBook.isPresent()) {
//			Books fbook =OBook.get();
//			return Optional.of(fbook);
//		}
//		return OBook;
//	}

	@Override
	public Books getBookByName(String bookName) throws BookNotFoundException {

		Optional<Books> book = bookRepository.findByName(bookName);
		if (book.isEmpty()) {
			throw new BookNotFoundException();
		} else
			return book.get();

	}

	@Override
	public Books updateBook(Books book) throws BookNotFoundException {

		Optional<Books> bookOptional = bookRepository.findById(book.getBookId());

		if (bookOptional.isPresent()) {
			Books book2 = bookOptional.get();
			book2.setBookName(book.getBookName());
			book2.setAuthor(book.getAuthor());
			book2.setBookPrice(book.getBookPrice());
			book2.setCategory(book.getCategory());
			return bookRepository.save(book2);

		} else {
			throw new BookNotFoundException();
		}

	}

}
