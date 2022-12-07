package com.morningstar.bookshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.morningstar.bookshop.entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Integer> {
	
	@Query("SELECT b FROM Books b WHERE b.bookName = ?1")
	public Optional<Books> findByName(String bookName);
	
	@Query("delete FROM Books WHERE bookName = ?1")
	public void deleteBookByName(String bookName);

}
