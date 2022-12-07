package com.morningstar.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morningstar.bookshop.entity.Books;
import com.morningstar.bookshop.entity.Orders;
import com.morningstar.bookshop.entity.Users;
import com.morningstar.bookshop.repository.BookRepository;
import com.morningstar.bookshop.repository.OrderRepository;
import com.morningstar.bookshop.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BookRepository bookRepo;
	@Override
	public Orders putOrder(Orders order) {
		
		return orderRepo.save(order);
	}

	@Override
	public List<Orders> getOrderByUserId(int userId) {
	
		return orderRepo.findAllOrdersByUserId(userId);
	}

	@Override
	public List<Orders> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public Orders placeOrder(String email,String bookName,int quantity) {
		Users user = userRepo.findUserByEmail(email);
		Books book = bookRepo.findByName(bookName).get();
		
		Orders order = new Orders();
		order.setUser(user);
		order.setBook(book);
		order.setQuantity(quantity);
		order.setTotalPrice(quantity*book.getBookPrice()); //multiplying quantity and book price and setting it
		return orderRepo.save(order);
	}

}
