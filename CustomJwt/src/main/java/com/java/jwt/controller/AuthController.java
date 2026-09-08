package com.java.jwt.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.jwt.dto.JwtResponse;
import com.java.jwt.dto.LoginRequest;
import com.java.jwt.enity.RegisterRequest;
import com.java.jwt.service.AuthService;
import com.java.jwt.util.JwtUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/auth")
@SecurityRequirement(name = "BearerAuth")

public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	private final AuthService authService;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AuthService authService) {

		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.authService = authService;
	}

	@PostMapping("/login")
	public JwtResponse login(@RequestBody LoginRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		String token = jwtUtil.generateToken(request.getUsername());

		return new JwtResponse(token);
	}

	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest request) {

		return authService.register(request);
	}

	@GetMapping("/hello")
	public String HelloWorld() {

		return "Hello world : welcome ";
	}
}