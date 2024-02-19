package br.com.joaogabriel.imagesrepo.service;

import java.util.UUID;

import br.com.joaogabriel.imagesrepo.entity.User;
import br.com.joaogabriel.imagesrepo.payload.request.UserPostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.UserGenericResponse;
import br.com.joaogabriel.imagesrepo.payload.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

	UserGenericResponse findByEmail(final String email);
	
	User getEmail(final String email);
	
	UserGenericResponse findByUsername(final String username);
	
	UserGenericResponse save(final UserPostRequest userPostRequest, final HttpServletRequest httpServletRequest);
	
	UserResponse findById(final UUID id);
	
	void checkingEmail(String email);
	
	void checkingUsername(String username);
}
