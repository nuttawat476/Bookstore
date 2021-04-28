package com.spring.demo.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.bookstore.model.OrderRequest;
import com.spring.demo.bookstore.model.OrderResponse;
import com.spring.demo.bookstore.model.User;
import com.spring.demo.bookstore.model.UserDetailResponse;
import com.spring.demo.bookstore.service.OrderServiceImpl;
import com.spring.demo.bookstore.service.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private OrderServiceImpl orderService;
	
	@GetMapping("/users")
	public ResponseEntity<UserDetailResponse> getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return new ResponseEntity<>(userService.getUserDetail(authentication.getName()), HttpStatus.OK);
	}
	
	@GetMapping("/allUser")
	public ResponseEntity<List<User>> findAllUser() {
		return new ResponseEntity<>(userService.findAllUserDetail(), HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity createUser(@RequestBody User user) {
		userService.createUserDetail(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/users")
	public ResponseEntity deleteOrder() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUser(authentication.getName());
		orderService.deleteAllOrder(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/users/orders")
	public ResponseEntity<OrderResponse> createOrders(@RequestBody OrderRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUser(authentication.getName());
		return new ResponseEntity<>(orderService.createOrderd(req, user),HttpStatus.OK);
	}
	


}
