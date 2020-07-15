package com.example.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.ResponseDto;

@ControllerAdvice
@RestController
public class GrobalExceptionHandler {
	
	//예외처리 
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> handlerArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value() , e.getMessage());
	}
}
