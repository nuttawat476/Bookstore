package com.spring.demo.bookstore.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.collections4.CollectionUtils;

import com.spring.demo.bookstore.model.Books;
import com.spring.demo.bookstore.model.OrderRequest;
import com.spring.demo.bookstore.model.OrderResponse;
import com.spring.demo.bookstore.model.Orders;
import com.spring.demo.bookstore.model.User;
import com.spring.demo.bookstore.repository.OrdersRepository;


@Service
public class OrderServiceImpl {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Transactional
	public OrderResponse createOrderd(OrderRequest req, User user) {
		List<Orders> orders = new ArrayList<>();
		BigDecimal sum = BigDecimal.ZERO;
		List<Books> bookSelected =  this.getAllBook().getBody().stream().filter(b -> req.getOrders().contains(b.getId())).collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(bookSelected)) {
			bookSelected.forEach(b -> {
				orders.add(Orders.builder()
						.userId(user.getId())
						.bookId(b.getId())
						.build());
			});
			sum = bookSelected.stream().map(Books::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
			ordersRepository.saveAll(orders);
		}
		return OrderResponse.builder().price(sum).build();
	}
	
	@Transactional
	public void deleteAllOrder(User user) {
		ordersRepository.deleteByUserId(user.getId());
	}
	
	private ResponseEntity<List<Books>> getAllBook() {
		ResponseEntity<List<Books>> responseEntity = 
				  restTemplate.exchange(
				    "https://scb-test-book-publisher.herokuapp.com/books",
				    HttpMethod.GET,
				    null,
				    new ParameterizedTypeReference<List<Books>>() {}
		);
		return responseEntity;
	}

}
