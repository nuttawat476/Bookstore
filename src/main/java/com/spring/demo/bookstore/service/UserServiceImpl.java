package com.spring.demo.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.demo.bookstore.model.Orders;
import com.spring.demo.bookstore.model.User;
import com.spring.demo.bookstore.model.UserDetailResponse;
import com.spring.demo.bookstore.repository.OrdersRepository;
import com.spring.demo.bookstore.repository.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrdersRepository orderRepository;
	
	public User getUser(String username) {	
		Optional<User> user = userRepository.findByUserName(username);
		return user.orElse(null);
	}
	
	public UserDetailResponse getUserDetail(String username) {	
		Optional<User> optUser = userRepository.findByUserName(username);
		User user = null;
		List<Long> books = new ArrayList<>();
		if(optUser.isPresent()) {
			user = optUser.get();
			List<Orders> orders = orderRepository.findByUserId(user.getId());
			books = orders.stream().map(Orders::getBookId).collect(Collectors.toList());
		}
		return null != user ? UserDetailResponse.builder().username(user.getUsername()).password(user.getPassword()).books(books).build() : null;
	}
	
	public List<User> findAllUserDetail() {	
		return userRepository.findAll();
	}
	
	@Transactional
	public void createUserDetail(User user) {
		user.setRoles("ROLE_USER");
		user.setActive(true);
		userRepository.save(user);
	}

}
