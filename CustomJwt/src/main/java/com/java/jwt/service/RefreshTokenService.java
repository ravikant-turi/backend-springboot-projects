package com.java.jwt.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.java.jwt.enity.RefreshToken;
import com.java.jwt.enity.User;
import com.java.jwt.repository.RefreshTokenRepository;
import com.java.jwt.util.JwtUtil;

@Service
public class RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;
	private final JwtUtil jwtUtil;

	/**
	 * Refresh Token validity: 7 Days
	 */
	private final long refreshTokenDurationMs = 7 * 24 * 60 * 60 * 1000L;

	public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, JwtUtil jwtUtil) {

		this.refreshTokenRepository = refreshTokenRepository;
		this.jwtUtil = jwtUtil;
	}

	/**
	 * Creates and stores a refresh token for a user.
	 *
	 * Flow: User Login ↓ Generate Refresh Token ↓ Set Expiry Date ↓ Save to
	 * Database ↓ Return RefreshToken Object
	 */
	public RefreshToken createRefreshToken(User user) {

		RefreshToken refreshToken = new RefreshToken();

		// Associate token with user
		refreshToken.setUser(user);

		// Generate unique token value
		refreshToken.setToken(UUID.randomUUID().toString());

		// Expiry = Current Time + 7 Days
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));

		// Save in database
		return refreshTokenRepository.save(refreshToken);
	}

	/**
	 * Find Refresh Token by token value.
	 *
	 * Example: findByToken("abc-123-xyz")
	 */
	public RefreshToken findByToken(String token) {

		return refreshTokenRepository.findByToken(token)
				.orElseThrow(() -> new RuntimeException("Refresh Token Not Found"));
	}

	/**
	 * Verifies whether refresh token is expired.
	 *
	 * If expired: Delete token from DB Throw Exception
	 *
	 * If valid: Return token
	 */
	public RefreshToken verifyExpiration(RefreshToken token) {

		if (token.getExpiryDate().isBefore(Instant.now())) {

			refreshTokenRepository.delete(token);

			throw new RuntimeException("Refresh Token Expired. Please login again.");
		}

		return token;
	}

	/**
	 * Delete refresh token.
	 *
	 * Useful during: Logout Account Deactivation
	 */
	public void deleteRefreshToken(RefreshToken token) {

		refreshTokenRepository.delete(token);
	}

	public String refreshAccessToken(String refreshToken) {

		RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
				.orElseThrow(() -> new RuntimeException("Refresh token not found"));

		if (token.getExpiryDate().isBefore(Instant.now())) {
			throw new RuntimeException("Refresh token expired");
		}

		return jwtUtil.generateToken(token.getUser().getUsername());
	}
}