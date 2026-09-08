package com.java.security.config;

import com.java.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class SecurityConfig {

	// Creates BCrypt password encoder.
	// Used to hash passwords during registration
	// and verify passwords during login.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Creates DaoAuthenticationProvider.
	// Responsibilities:
	// - Load user from database using CustomUserDetailsService
	// - Verify password using PasswordEncoder
	// - Create authenticated Authentication object
	// Used during login authentication.
	@Bean
	public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

		provider.setPasswordEncoder(passwordEncoder);

		return provider;
	}

	// Creates AuthenticationManager.
	// Responsibilities:
	// - Receive authentication requests
	// - Delegate authentication to AuthenticationProvider
	// Flow:
	// AuthenticationManager -> DaoAuthenticationProvider
	@Bean
	public AuthenticationManager authenticationManager(DaoAuthenticationProvider authenticationProvider) {

		return new ProviderManager(authenticationProvider);
	}

	// Builds and configures the Spring Security Filter Chain.

	// Configures:

	// - CSRF protection

	// - Authorization rules

	// - Session management

	// - JWT authentication
	// This is the main Spring Security configuration.
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
			JwtAuthenticationConverter jwtAuthenticationConverter) {

		httpSecurity.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/register").permitAll()
						.requestMatchers("/api/auths/login").permitAll()

						.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.oauth2ResourceServer(
						oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));

		return httpSecurity.build();
	}

	// Creates JwtAuthenticationConverter.
	// Responsibilities:
	// - Extract authorities from JWT claims
	// - Convert JWT claims into GrantedAuthority objects
	// - Populate Authentication object with roles/authorities
	// Example:
	// "authorities":["ADMIN","USER"]

	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {

		JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();

		authoritiesConverter.setAuthoritiesClaimName("authorities");

		authoritiesConverter.setAuthorityPrefix("");

		JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();

		authenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

		return authenticationConverter;
	}

	// Creates SecretKey from Base64 encoded secret.
	// Responsibilities:
	// - Provide signing key for JWT generation
	// - Provide validation key for JWT verification
	// Used by JwtEncoder and JwtDecoder.

	@Bean
	public SecretKey jwtSecretKey(@Value("${jwt.secret}") String secret) {

		byte[] decodedKey = Base64.getDecoder().decode(secret);

		return new SecretKeySpec(decodedKey, "HmacSHA256");
	}

	// Creates JwtEncoder.
	// Responsibilities:
	// - Generate JWT access tokens
	// - Sign JWT using HS256 algorithm
	// Used after successful login.

	@Bean
	public JwtEncoder jwtEncoder(SecretKey secretKey) {

		return NimbusJwtEncoder.withSecretKey(secretKey).algorithm(MacAlgorithm.HS256).build();
	}

	// Creates JwtDecoder.
	// Responsibilities:
	// - Validate JWT signature
	// - Validate token expiration
	// - Validate issuer claim
	// - Decode JWT payload
	// Automatically used by Spring Security when
	// processing Bearer tokens.

	@Bean
	public JwtDecoder jwtDecoder(SecretKey secretKey, @Value("${jwt.issuer}") String issuer) {

		NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();

		decoder.setJwtValidator(JwtValidators.createDefaultWithIssuer(issuer));

		return decoder;
	}
}