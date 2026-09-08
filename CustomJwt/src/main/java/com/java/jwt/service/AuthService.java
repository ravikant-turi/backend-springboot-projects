package com.java.jwt.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.jwt.enity.RegisterRequest;
import com.java.jwt.enity.User;
import com.java.jwt.util.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository repository,
            PasswordEncoder passwordEncoder) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request) {

        if(repository.findByUsername(
                request.getUsername()).isPresent()) {

            throw new RuntimeException(
                    "Username already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        user.setRole(request.getRole());

        repository.save(user);

        return "User Registered Successfully";
    }
}