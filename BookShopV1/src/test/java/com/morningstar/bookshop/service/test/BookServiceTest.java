package com.morningstar.bookshop.service.test;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.morningstar.bookshop.constants.Category;
import com.morningstar.bookshop.entity.Books;
import com.morningstar.bookshop.repository.BookRepository;
import com.morningstar.bookshop.service.BookServiceImpl;

@SpringBootTest(classes = { BookServiceTest.class })
class BookServiceTest {

	@Mock
	BookRepository bookRepository;

	@InjectMocks
	BookServiceImpl bookSevice;
	

	@Test
	@Order(1)
	void testaddBook() {
		List<Books> bookList = new ArrayList<>();
		bookList.add(new Books(1, "book1", "author1", 150, Category.EDUCATIONAL));

		when(bookRepository.findAll()).thenReturn(bookList);
		Assertions.assertEquals(1, bookRepository.findAll().size());
	}

	@Test
	@Order(2)
	void testGetBookByName() {
		List<Books> bookList = new ArrayList<>();
		bookList.add(new Books(1, "book1", "author1", 150, Category.EDUCATIONAL));
		String bookName = "book1";
		Books book =null;
		for (Books b : bookList) {

			if (b.getBookName().equals(bookName)) {
				book = b;

				break;
			}
		}
		System.out.println(book);
		when(bookRepository.findByName(bookName)).thenReturn(Optional.ofNullable(book));
		Assertions.assertEquals(true, bookRepository.findByName(bookName).isPresent());
	}

}
