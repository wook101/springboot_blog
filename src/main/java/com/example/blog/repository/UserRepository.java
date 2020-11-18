package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.model.User;


// DAO
// 스프링부트가 자동으로 Bean등록함
// @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{

	
}

	/*
	//JPA Naming 쿼리
	//SELECT * FROM user WHERE username = ? AND password = ?
	User findByUsernameAndPassword(String username, String password);
	/* native방식
	@Query(value="SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery=true)
	User login(String username, String password);	
	*/
