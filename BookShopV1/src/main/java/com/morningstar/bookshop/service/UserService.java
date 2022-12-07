package com.morningstar.bookshop.service;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import com.morningstar.bookshop.entity.Users;

@Service
public interface UserService {
	
	public Users getUserByEmail(String email);
	
	public String addUser(Users user);
	
	public List<Users> getAllUsers();
	
	public String loginByEmailandPassword(String email,String password);
	
}
