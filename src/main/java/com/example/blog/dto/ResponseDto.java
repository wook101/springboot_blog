package com.example.blog.dto;


import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ResponseDto<T> {
	int status;
	T data;
}
