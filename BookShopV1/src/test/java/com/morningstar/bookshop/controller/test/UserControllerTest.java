package com.morningstar.bookshop.controller.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.morningstar.bookshop.controller.UserController;
import com.morningstar.bookshop.entity.Users;
import com.morningstar.bookshop.service.UserServiceImpl;

@SpringBootTest(classes=UserControllerTest.class)
class UserControllerTest {
	
	@Mock
	private UserServiceImpl userService;
	
	@InjectMocks
	UserController userController;
	
	public List<Users> users;
	@Test
	void testUserLogin() {
		users = new ArrayList<>();
		 users.add(new Users(1, "rohan", "patil", "rohan@gmail.com", "1234567890","rohan#321"));
		 users.add(new Users(1, "user", "two", "user@gmail.com", "1234567890","user#321"));
		 
		 String email = "rohan@gmail.com";
		 String password ="rohan#321";
		 String loginMessage="fail";
		 for(Users u:users) {
			 if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				 loginMessage="success";
				 break;
			 }
		 }
		 
		when(userService.loginByEmailandPassword("rohan@gmail.com", "rohan#321")).thenReturn(loginMessage);
		Assertions.assertEquals("success", userService.loginByEmailandPassword("rohan@gmail.com", "rohan#321"));
	}

}
