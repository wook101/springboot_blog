package com.example.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.ResponseDto;
import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	UserService userService;
	
	//회원가입 요청
	@PostMapping("/api/join")
	public ResponseDto<String> joinApi(@RequestBody User requestUser) {
		requestUser.setRole(RoleType.USER);
		userService.join(requestUser);
		return new ResponseDto<String>(HttpStatus.OK.value(), "회원가입 완료");
	}
	//로그인 요청
	@PostMapping("/api/login")
	public ResponseDto<String> loginApi(@RequestBody User requestUser, HttpSession session){
		User user = userService.login(requestUser.getUsername(), requestUser.getPassword());
		if (user!=null) { //로그인 성공
			session.setAttribute("user", "user");
		}
		return new ResponseDto<String>(HttpStatus.OK.value(), "로그인 완료");
	}
}
