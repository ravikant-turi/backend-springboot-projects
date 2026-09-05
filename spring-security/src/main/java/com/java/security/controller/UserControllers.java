package com.java.security.controller;

import com.java.security.dto.UserRegisterRequestDto;
import com.java.security.dto.UserRegisterResponseDto;
import com.java.security.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserControllers {

	private final AuthService authService;

	public UserControllers(AuthService authService) {

		this.authService = authService;
	}

	@PostMapping("/register")
	ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
		System.out.println("===================we are in the controller============");
		UserRegisterResponseDto response = this.authService.registerUser(userRegisterRequestDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@GetMapping("/hello")
	public ResponseEntity<String>  hello(){
		return ResponseEntity.status(HttpStatus.OK).body("Hello world");
	}




}
