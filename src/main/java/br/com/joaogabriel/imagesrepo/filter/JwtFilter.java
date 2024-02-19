package br.com.joaogabriel.imagesrepo.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.joaogabriel.imagesrepo.entity.User;
import br.com.joaogabriel.imagesrepo.jwt.JwtService;
import br.com.joaogabriel.imagesrepo.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	private final UserService userService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public JwtFilter(JwtService jwtService, UserService userService) {
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		if (token != null) {
			String  emailAuthenticatedUser = jwtService.getEmailFromToken(token);
			logger.info("Verifying token for user {} authentication. Token: {}", emailAuthenticatedUser, token);
			User user = userService.getEmail(emailAuthenticatedUser);
			setUserAsAuthenticated(user);
		}
		filterChain.doFilter(request, response);
	}
	
	private void setUserAsAuthenticated(User user) {
		UserDetails userDetails = org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getHashPassword())
				.roles("USER")
				.build();
		
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private String getToken(HttpServletRequest request) {
		String authFromHeader = request.getHeader("Authorization");
		if (authFromHeader != null) {
			String[] bearerToken = authFromHeader.split(" ");
			if(bearerToken.length == 2)  return bearerToken[1];
		}
		return null;
	}

}
