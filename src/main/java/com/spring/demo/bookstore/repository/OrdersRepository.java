package com.spring.demo.bookstore.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.demo.bookstore.model.Orders;
import com.spring.demo.bookstore.model.User;


@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{
	
	@Query("SELECT u FROM User u WHERE u.username = ?1") 
	Optional<User> findByUserName(String userName);
	
	List<Orders> findByUserId(Long userId);
	
	void deleteByUserId(Long userId);
}
