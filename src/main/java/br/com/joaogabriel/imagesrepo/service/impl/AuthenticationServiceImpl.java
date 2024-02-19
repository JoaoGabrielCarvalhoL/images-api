package br.com.joaogabriel.imagesrepo.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.joaogabriel.imagesrepo.entity.User;
import br.com.joaogabriel.imagesrepo.exception.AuthenticationPasswordException;
import br.com.joaogabriel.imagesrepo.exception.ResourceNotFoundException;
import br.com.joaogabriel.imagesrepo.jwt.JwtService;
import br.com.joaogabriel.imagesrepo.jwt.response.TokenResponse;
import br.com.joaogabriel.imagesrepo.payload.request.AuthenticateRequest;
import br.com.joaogabriel.imagesrepo.repository.UserRepository;
import br.com.joaogabriel.imagesrepo.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final JwtService jwtService;
	
	public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@Override
	public TokenResponse authenticate(AuthenticateRequest authenticateRequest) {
		logger.info("Authentication for the user: {}", authenticateRequest.email());
		Optional<User> userOptional = userRepository.findByEmail(authenticateRequest.email());
		if (userOptional.isEmpty()) {
			throw new ResourceNotFoundException("User not found into database. Email: " + authenticateRequest.email());
		}
		User user = userOptional.get();
		boolean matches = this.passwordEncoder.matches(authenticateRequest.password(), user.getHashPassword());
		if (matches) {
			return this.jwtService.generate(user);
		} else {
			throw new AuthenticationPasswordException("Unable to authenticate the user: " + authenticateRequest.email());
		}
		
	}

}
