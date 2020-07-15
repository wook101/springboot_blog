package com.example.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	//회원가입
	@Transactional
	public void join(User user) {
		userRepository.save(user);	
	}
	
	//로그인
	@Transactional(readOnly = true) //select의경우 읽기전용으로 정합성 유지, 쓰기작업이 일어나는것을 방지하기위해
	public User login(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
}
