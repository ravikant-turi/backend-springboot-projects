package com.java.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/auth")
public class StudentController {
    @GetMapping
    public ResponseEntity<String> getAllStudent() {
        String str = "get all student ";

        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Authentication authentication) {

        System.out.println("========== SECURITY INFORMATION ==========");

        System.out.println("Principal          : " + principal);
        System.out.println("Principal Name     : " + principal.getName());

        System.out.println("Is Authenticated   : " + authentication.isAuthenticated());
        System.out.println("Authentication Name: " + authentication.getName());
        System.out.println("Authorities        : " + authentication.getAuthorities());
        System.out.println("Credentials        : " + authentication.getCredentials());
        System.out.println("Details            : " + authentication.getDetails());
        System.out.println("Principal          : " + authentication.getPrincipal());

        System.out.println("==========================================");

        return principal.getName();
    }
    @GetMapping("hello")
    public String helloWorld(){
        return "Hello security";
    }
}
