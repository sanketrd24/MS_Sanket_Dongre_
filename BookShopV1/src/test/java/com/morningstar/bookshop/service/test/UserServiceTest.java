package com.morningstar.bookshop.service.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.morningstar.bookshop.entity.Users;
import com.morningstar.bookshop.repository.UserRepository;
import com.morningstar.bookshop.service.BookServiceImpl;

@SpringBootTest(classes=UserServiceTest.class)
class UserServiceTest {
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	BookServiceImpl bookServiceImpl;
	
	@Test
	void testGetAllUsers() {
		List<Users> users = new ArrayList<>();
		users.add(new Users(1, "first", "user", "fisrtuser@gmail.com", "12234567890", "firstuser@123"));
		users.add(new Users(2, "second", "user", "seconduser@gmail.com", "4897153245", "seconduser@123"));
		users.add(new Users(3, "third", "user", "thirduser@gmail.com", "8745612347", "thirduser@123"));
		
		when(userRepository.findAll()).thenReturn(users);
		Assertions.assertEquals(3, userRepository.findAll().size());
	}

}
