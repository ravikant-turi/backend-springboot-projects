package com.java.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.java.jwt.filter.JwtAuthenticationFilter;
import com.java.jwt.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;
	private final JwtAuthenticationFilter jwtFilter;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthenticationFilter jwtFilter) {

		this.customUserDetailsService = customUserDetailsService;
		this.jwtFilter = jwtFilter;
	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//		return http
//
//				.csrf(csrf -> csrf.disable())
//
//				.authorizeHttpRequests(auth -> auth
//
//						.requestMatchers("/api/auth/register").permitAll().requestMatchers("/api/auth/login")
//						.permitAll().requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
//						.requestMatchers("/api/test/**").permitAll().anyRequest().authenticated()
//
//				)
//
//				.sessionManagement(session -> session
//
//						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.addFilterBefore(
//
//						jwtFilter, UsernamePasswordAuthenticationFilter.class)
//
//				.build();
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http

				.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> auth

				        .requestMatchers(
				                "/api/auth/register",
				                "/api/auth/login",
				                "/swagger-ui/**",
				                "/v3/api-docs/**"
				        ).permitAll()

				        .requestMatchers("/api/admin/**")
				        .hasRole("ADMIN")

				        .requestMatchers("/api/user/**")
				        .hasRole("USER")

				        .anyRequest()
				        .authenticated()
				)

				.sessionManagement(session -> session

						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(

						jwtFilter, UsernamePasswordAuthenticationFilter.class)

				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

		return config.getAuthenticationManager();
	}
}