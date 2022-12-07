package com.morningstar.bookshop.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.morningstar.bookshop.entity.Books;
import com.morningstar.bookshop.exception.BookAlreadyExistsException;
import com.morningstar.bookshop.exception.BookNotFoundException;

@Service
public interface BookService {
	
	public String addBook(Books book) throws BookAlreadyExistsException;
	
	public List<Books> getAllBooks();
	
	public Books getBookByName(String bookName) throws BookNotFoundException ;
	
	public void deleteBookByBookId(String bookName) throws BookNotFoundException;
	
	public Books updateBook(Books book) throws BookNotFoundException;
	
//	public void updateBookByBookId(int bookId);
}
