package com.morningstar.bookshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.morningstar.bookshop.entity.Orders;
import com.morningstar.bookshop.entity.Users;

@Service
public interface OrderService {
	
	public Orders putOrder(Orders order);
	
	public List<Orders> getOrderByUserId(int userId);
	
	public List<Orders> getAllOrders();
	
	//-------------------------
	public Orders placeOrder(String email,String bookName,int quantity);
}
