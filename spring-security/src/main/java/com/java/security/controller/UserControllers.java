package com.java.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.security.dto.UserRegisterRequestDto;
import com.java.security.dto.UserRegisterResponseDto;
import com.java.security.service.AuthService;

@RestController
@RequestMapping("api/auth")
public class UserControllers {

	private AuthService authService;

	public UserControllers(AuthService authService) {

		this.authService = authService;
	}

	@PostMapping
	ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {

		UserRegisterResponseDto response = this.authService.registerUser(userRegisterRequestDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

}
