package com.spring.demo.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.bookstore.model.Books;
import com.spring.demo.bookstore.model.User;
import com.spring.demo.bookstore.service.BookServiceImpl;
import com.spring.demo.bookstore.service.UserServiceImpl;

@RestController
public class BookController {
	
	@Autowired
	private BookServiceImpl bookService;
	
	@GetMapping("/books")
	public ResponseEntity<List<Books>> getUserDetails() {
		return new ResponseEntity<List<Books>>(bookService.getBooks(), HttpStatus.OK);
	}


}
