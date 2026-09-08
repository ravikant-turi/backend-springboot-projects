package com.java.jwt.util.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jwt.enity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

}