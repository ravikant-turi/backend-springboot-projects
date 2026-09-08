package com.java.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/test")
@SecurityRequirement(name = "BearerAuth")
public class TestController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello User";
	}
}