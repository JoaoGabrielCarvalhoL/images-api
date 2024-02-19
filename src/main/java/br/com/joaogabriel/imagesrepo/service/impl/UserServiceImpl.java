package br.com.joaogabriel.imagesrepo.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.joaogabriel.imagesrepo.entity.User;
import br.com.joaogabriel.imagesrepo.exception.ResourceAlreadyUsedException;
import br.com.joaogabriel.imagesrepo.exception.ResourceNotFoundException;
import br.com.joaogabriel.imagesrepo.mapper.UserMapper;
import br.com.joaogabriel.imagesrepo.payload.request.UserPostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.UserGenericResponse;
import br.com.joaogabriel.imagesrepo.payload.response.UserResponse;
import br.com.joaogabriel.imagesrepo.repository.UserRepository;
import br.com.joaogabriel.imagesrepo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final UserMapper userMapper;
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
		this.userMapper = userMapper;
		this.userRepository = userRepository;
	}

	@Override
	public UserGenericResponse findByEmail(String email) {
		logger.info("Getting user by email: {}", email);
		return this.userRepository.findByEmail(email).map(userMapper::toUserGenericResponse)
			.orElseThrow(() -> new ResourceNotFoundException("User not found into database. Email: " + email));
	}

	@Override
	public UserGenericResponse findByUsername(String username) {
		logger.info("Getting user by username: {}", username);
		return this.userRepository.findByUsername(username).map(userMapper::toUserGenericResponse)
				.orElseThrow(() -> new ResourceNotFoundException("User not found into database. Username: " + username));
	}

	@Override
	public UserGenericResponse save(UserPostRequest userPostRequest, HttpServletRequest httpServletRequest) {
		checkingEmail(userPostRequest.email());
		checkingUsername(userPostRequest.username());
		logger.info("Saving user into database. User: {}", userPostRequest);
		User user = userMapper.toUser(userPostRequest, httpServletRequest);
		User saved = this.userRepository.save(user);
		return userMapper.toUserGenericResponse(saved);
	}

	@Override
	public UserResponse findById(UUID id) {
		logger.info("Getting user by id: {}", id);
		return this.userRepository.findById(id).map(userMapper::toUserResponse)
				.orElseThrow(() -> new ResourceNotFoundException("User not found into database. Id: " + id));
	}

	@Override
	public void checkingEmail(String email) {
		logger.info("Checking email availability: {}", email);
		Optional<User> user = this.userRepository.findByEmail(email);
		if (user.isPresent()) {
			throw new ResourceAlreadyUsedException("Resource already in used. Email: " + email);
		}
	}

	@Override
	public void checkingUsername(String username) {
		logger.info("Checking username availability: {}", username);
		Optional<User> user = this.userRepository.findByUsername(username);
		if (user.isPresent()) {
			throw new ResourceAlreadyUsedException("Resource already in used. Username: " + username);
		}
	}

	@Override
	public User getEmail(String email) {
		return this.userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Invalid user."));
	}
}
