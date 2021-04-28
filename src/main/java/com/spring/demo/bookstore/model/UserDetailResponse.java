package com.spring.demo.bookstore.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponse {
	
	private String username;
	private String password;
	private String dateOfBirth;
	private List<Long> books;
	
}
