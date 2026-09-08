package com.java.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    /**
     * Secret key used to sign and verify JWT tokens.
     * Both token generation and validation use this key.
     */
    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkeymysecretkey";

    /**
     * Converts the secret string into an HMAC SHA key
     * required by the JJWT library for signing tokens.
     */
    private final Key key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * Generates a JWT token for the given username.
     *
     * Token contains:
     * - Subject (username)
     * - Issued time
     * - Expiration time (1 hour)
     * - Digital signature
     *
     * @param username authenticated user's username
     * @return generated JWT token
     */
    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )
                .signWith(key)
                .compact();
    }

    /**
     * Extracts the username (subject) from the JWT token.
     *
     * @param token JWT token
     * @return username stored inside token
     */
    public String extractUsername(String token) {

        return extractAllClaims(token)
                .getSubject();
    }

    /**
     * Validates the token by checking:
     * 1. Username inside token matches expected username.
     * 2. Token has not expired.
     *
     * @param token JWT token
     * @param username expected username
     * @return true if token is valid, otherwise false
     */
    public boolean isTokenValid(
            String token,
            String username) {

        String extractedUsername =
                extractUsername(token);

        return extractedUsername.equals(username)
                && !isTokenExpired(token);
    }

    /**
     * Checks whether the token expiration time
     * is before the current time.
     *
     * @param token JWT token
     * @return true if token is expired, otherwise false
     */
    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    /**
     * Parses the JWT token and extracts all claims
     * after verifying its signature.
     *
     * Claims may contain:
     * - Subject (username)
     * - Issued At
     * - Expiration
     * - Custom claims (if added)
     *
     * @param token JWT token
     * @return Claims object containing token payload data
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}