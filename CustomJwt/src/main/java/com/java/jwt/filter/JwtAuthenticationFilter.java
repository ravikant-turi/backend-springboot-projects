package com.java.jwt.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.java.jwt.service.CustomUserDetailsService;
import com.java.jwt.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT Authentication Filter
 *
 * This filter executes once for every incoming request.
 *
 * Responsibilities: 1. Read JWT token from Authorization header. 
 * 2. Extract  username from token.
 *  3. Load user details from database. 
 *  4. Validate the token.
 *  5. Create Authentication object. 
 *  6. Store Authentication in SecurityContextHolder.
 *  7. Allow request to proceed to the next filter.
 *
 * If token is missing or invalid, request continues without authentication and
 * Spring Security will decide whether access should be granted or denied.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	private final CustomUserDetailsService userDetailsService;

	public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {

		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	/**
	 * Called automatically for every HTTP request.
	 *
	 * Flow: Request ↓ Extract JWT Token ↓ Extract Username ↓ Load User From
	 * Database ↓ Validate Token ↓ Create Authentication Object ↓ Save
	 * Authentication In SecurityContext ↓ Continue Filter Chain
	 *
	 * @param request     incoming HTTP request
	 * @param response    outgoing HTTP response
	 * @param filterChain remaining filters in the chain
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Step 1:
		// Read Authorization header.
		//
		// Example:
		// Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
		String authHeader = request.getHeader("Authorization");

		// Step 2:
		// If Authorization header is missing
		// OR does not start with "Bearer ",
		// skip JWT authentication and move to next filter.
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {

			filterChain.doFilter(request, response);
			return;
		}

		// Step 3:
		// Remove "Bearer " prefix and extract actual JWT token.
		//
		// Example:
		// Bearer abc.xyz.123
		//
		// Extracted:
		// abc.xyz.123
		String token = authHeader.substring(7);

		// Step 4:
		// Extract username from JWT payload.
		String username = jwtUtil.extractUsername(token);

		// Step 5:
		// Continue only if:
		// 1. Username was extracted successfully.
		// 2. User is not already authenticated.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// Step 6:
			// Load user details from database using username.
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			// Step 7:
			// Validate token.
			//
			// Checks:
			// - Username matches
			// - Token not expired
			if (jwtUtil.isTokenValid(token, userDetails.getUsername())) {

				// Step 8:
				// Create Authentication object.
				//
				// Principal = userDetails
				// Credentials = null
				// Authorities = roles/permissions
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				// Step 9:
				// Attach request information such as:
				// - Remote IP
				// - Session details
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// Step 10:
				// Store Authentication object into
				// SecurityContextHolder.
				//
				// After this step, Spring Security
				// considers the user authenticated.
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		// Step 11:
		// Continue execution of remaining filters
		// and eventually reach the controller.
		filterChain.doFilter(request, response);
	}
}