package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	//홈 화면
	@GetMapping("/")
	public String getHome() {
		return "home";
	}

	// 회원가입 화면
	@GetMapping("/join")
	public String getJoin() {
		return "join";
	}

	// 로그인 화면
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

}
