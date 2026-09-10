package com.java.jwt.service;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.jwt.enity.RegisterRequest;
import com.java.jwt.enity.Role;
import com.java.jwt.enity.User;
import com.java.jwt.repository.RoleRepository;
import com.java.jwt.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthService(
            UserRepository repository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public String register(RegisterRequest request) {

        if (repository.findByUsername(
                request.getUsername()).isPresent()) {

            throw new RuntimeException(
                    "Username already exists");
        }

        Role userRole = roleRepository
                .findByName("ROLE_USER")
                .orElseThrow(() ->
                        new RuntimeException(
                                "ROLE_USER not found"));

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        user.setRoles(
                Set.of(userRole));

        repository.save(user);

        return "User Registered Successfully";
    }
}