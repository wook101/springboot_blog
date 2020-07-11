package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.User;


// DAO
// 스프링부트가 자동으로 Bean등록함
// @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{

}
