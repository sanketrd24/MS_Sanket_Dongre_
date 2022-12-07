package com.morningstar.bookshop.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.morningstar.bookshop.constants.Category;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.Order;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
public class Books {

	public Books() {
		super();
	}

	public Books(int bookId, @NotBlank(message = "Please provide Book Name") String bookName,
			@NotBlank(message = "Please provide Author Name") String author,
			@Min(value = 1, message = "Book Price Should be Greater than 0") @NotNull(message = "Enter valid book Price") double bookPrice,
			@NotNull(message = "Please Enter Category") Category category) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.bookPrice = bookPrice;
		this.category = category;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int bookId;

	@NotBlank(message = "Please provide Book Name")
	@Column(name = "book_name")
	private String bookName;

	@NotBlank(message = "Please provide Author Name")
	@Column(name = "author")
	private String author;

	@Min(value = 1, message = "Book Price Should be Greater than 0")
//	@Positive(message="Book Price should not be less than 0")
	@NotNull(message = "Enter valid book Price")
	@Column(name = "book_price")
	private double bookPrice;

	@NotNull(message = "Please Enter Category")
	@Column(name = "book_category")
	private Category category;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Books [bookId=" + bookId + ", bookName=" + bookName + ", author=" + author + ", bookPrice=" + bookPrice
				+ ", description=" + category + "]";
	}

}
