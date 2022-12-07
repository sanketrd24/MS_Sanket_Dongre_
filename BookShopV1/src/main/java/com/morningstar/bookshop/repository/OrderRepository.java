package com.morningstar.bookshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.morningstar.bookshop.entity.Orders;
import com.morningstar.bookshop.entity.Users;
@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
	@Query("select o from Orders o where o.user.userId=?1")
//	@Query("select * from Orders where Users.userId=?1 ")
	public List<Orders> findAllOrdersByUserId(int userId);
}
