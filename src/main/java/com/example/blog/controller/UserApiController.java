package com.example.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
	@PostMapping("/auth/joinPost")
	public ResponseDto<String> joinApi(@RequestBody User requestUser) {
		System.out.println(requestUser);
		requestUser.setRole(RoleType.USER);
		userService.join(requestUser);
		return new ResponseDto<String>(HttpStatus.OK.value(), "회원가입 완료");
	}
	
	//카카오 로그인 인증요청 콜백
	@GetMapping("/oauth/kakao/login/callback")
	public String loginCallback(String code) {
		
		//카카오 서버로 accessToken을 받기위한 post요청하기
		//RestTemplated생성
		//HttpHeaders에 contentType 추가하기
		//MultivalueMap생성하고 body내용 추가하기
		
		return "로그인 인증 요청 콜백 code: "+code;
	}
	

}
