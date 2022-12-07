package com.morningstar.bookshop.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morningstar.bookshop.entity.Users;
import com.morningstar.bookshop.exception.InvalidLoginCredentialException;
import com.morningstar.bookshop.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public Users getUserByEmail(String email) {

		return userRepo.findUserByEmail(email);
	}

//	@Override
//	public String addUser(Users user) {
//		String emailRegex = "[a-zA-Z0-9]+@{1}[a-zA-Z0-9]+\\.[a-zA-Z]";
//		String phoneNoRegex = "[0-9]{10}";
//		if (Pattern.matches(phoneNoRegex, user.getPhoneNo())) {
//			if (Pattern.matches(emailRegex, user.getEmail())) {
//				Optional<Users> user1 = userRepo.findUserByEmailAndPass(user.getEmail(), user.getPassword());
//				if (user1.isPresent()) {
//					return "User already exist";
//
//				} else {
//					userRepo.save(user);
//					return "Registered Succesfully";
//				}
//			} else
//				return "enter valid email ex.abc@gmail.com";
//		} else
//			return "enter valid phone no.";
//
//	}

	@Override
	public String addUser(Users user) {

		Optional<Users> user1 = userRepo.findUserByEmailAndPass(user.getEmail(), user.getPassword());
		if (user1.isPresent()) {
			return "User already exist";

		} else {
			userRepo.save(user);
			return "Registered Succesfully";
		}

	}

	@Override
	public List<Users> getAllUsers() {

		return userRepo.findAll();
	}

	@Override
	public String loginByEmailandPassword(String email, String password) {

		Optional<Users> user = userRepo.findUserByEmailAndPass(email, password);
		if (user.isEmpty()) {
			throw new InvalidLoginCredentialException();
		} else
			return "Log in successfull";
	}

}
