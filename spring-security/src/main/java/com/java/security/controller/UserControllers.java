package com.java.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import com.java.security.dto.UserRegisterRequestDto;
import com.java.security.dto.UserRegisterResponseDto;
import com.java.security.service.AuthService;

@RestController
@RequestMapping("api/auth")
public class UserControllers {

	private final AuthService authService;

	public UserControllers(AuthService authService) {

		this.authService = authService;
	}

	@PostMapping("/register")
	ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
		System.out.println("===================we are in the controller");
		UserRegisterResponseDto response = this.authService.registerUser(userRegisterRequestDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}
	@GetMapping
	public ResponseEntity<String>  hello(){
		return ResponseEntity.status(HttpStatus.OK).body("Hello world");
	}
	@GetMapping("/token")
	public CsrfToken getCSRF(CsrfToken csrfToken){
		return csrfToken;
	}
	
	@PostMapping("/login")
    public ResponseEntity<Boolean> login(
            @RequestBody UserRegisterRequestDto registerRequestDto) {
        Boolean loggedIn = authService.login(registerRequestDto);

        return ResponseEntity.ok(loggedIn);
    }


}
