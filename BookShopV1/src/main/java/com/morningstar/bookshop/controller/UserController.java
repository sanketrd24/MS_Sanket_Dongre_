package com.morningstar.bookshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.morningstar.bookshop.entity.Books;
import com.morningstar.bookshop.entity.Orders;
import com.morningstar.bookshop.entity.Users;
import com.morningstar.bookshop.exception.BookNotFoundException;
import com.morningstar.bookshop.service.BookService;
import com.morningstar.bookshop.service.OrderService;
import com.morningstar.bookshop.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;

	@Autowired
	private BookService bookService;
	
	//Logger
		private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/email/{email}")
	public ResponseEntity<Users> getUser(@PathVariable("email") String email) {
		return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.FOUND);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Users user) {
		logger.info("User Login : "+"Email : "+user.getEmail()+" ,Password : "+user.getPassword());
		String email = user.getEmail();
		String password = user.getPassword();
		return new ResponseEntity<>(userService.loginByEmailandPassword(email, password),HttpStatus.ACCEPTED);
	}

	@PostMapping("/register")
	public ResponseEntity<String> addUser(@Valid @RequestBody Users user) {
		logger.info("User Registration : "+"Email : "+user.getEmail()+" ,Name : "+user.getFname()+" "+user.getLname());
		return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
	}

//	@PostMapping("/order")
//	public ResponseEntity<?> order(@RequestBody Orders order){
//		
//		return ResponseEntity.ok().body(orderService.putOrder(order));
//	}

	//Get Orders of a user by user Id
	@GetMapping("/getallorders/{userId}")
	public ResponseEntity<List<Orders>> getOrdersByUserId(@PathVariable("userId") int userId) {

		return new ResponseEntity<>(orderService.getOrderByUserId(userId),HttpStatus.OK);
	}

	// Get book by name
	@GetMapping("/getbookbyname/{bookName}")
	public ResponseEntity<Books> getBookByName(@PathVariable("bookName") String bookName) throws BookNotFoundException {

		Books book = null;
		book = bookService.getBookByName(bookName);
		return ResponseEntity.of(Optional.of(book));
	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Books>> getAllBooks() {
		return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
	}
	
	//To place order
	@PostMapping("/placeorder")
	public ResponseEntity<Orders> placeOrder(@RequestParam("email") String email, @RequestParam("bookName") String bookName,
			@RequestParam("quantity") int quantity) {
		Orders order= orderService.placeOrder(email, bookName, quantity);
		logger.info("User Placing Order : "+"Order Id : "+order.getOrderId()+" ,Total Price : "+order.getTotalPrice());
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	//validation message handling 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	   Map<String, String> errors = new HashMap<>();
	 
	   ex.getBindingResult().getFieldErrors().forEach(error ->
	           errors.put(error.getField(), error.getDefaultMessage()));
	 
	   return errors;
	}
}
