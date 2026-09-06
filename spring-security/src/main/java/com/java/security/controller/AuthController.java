package com.java.security.controller;

import com.java.security.dto.LoginRequestDto;
import com.java.security.dto.LoginResponseDto;
import com.java.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auths")
public class AuthController {

    @Autowired
    private   JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto>login(@RequestBody LoginRequestDto loginRequestDto){

        Authentication authenticationRequest=
                UsernamePasswordAuthenticationToken
                        .unauthenticated
                                (loginRequestDto.getUsername(),
                                loginRequestDto.getPassword());

        Authentication authentication=
                authenticationManager.
            authenticate(authenticationRequest)
                ;
     String token= jwtService.generateToken(authentication);

       return ResponseEntity.status(HttpStatus.CREATED).body(new LoginResponseDto(token));
    }
    @GetMapping("/hello")
    public String sayHello(Authentication authentication) {
        return "Hello, you are logged in as : " + authentication.getName();
    }

}
