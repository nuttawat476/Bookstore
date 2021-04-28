package com.spring.demo.bookstore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.demo.bookstore.model.Books;


@Service
public class BookServiceImpl {

	@Autowired
	private RestTemplate restTemplate;
	
	public List<Books> getBooks() {	
		List<Books> allBook = this.getAllBook().getBody();
		List<Books> reccBook = this.getRecommendBooks().getBody();
		reccBook.forEach(b -> b.setIsRecommended(Boolean.TRUE));
		
		List<Books> notReccBook = allBook.stream().filter(b -> !reccBook.contains(b)).map(b -> {
			b.setIsRecommended(Boolean.FALSE);
			return b;
		}).collect(Collectors.toList());
		
		reccBook.addAll(notReccBook);
		return reccBook;
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
	
	private ResponseEntity<List<Books>> getRecommendBooks() {
		ResponseEntity<List<Books>> responseEntity = 
				  restTemplate.exchange(
				    "https://scb-test-book-publisher.herokuapp.com/books/recommendation",
				    HttpMethod.GET,
				    null,
				    new ParameterizedTypeReference<List<Books>>() {}
		);
		return responseEntity;
	}

}
