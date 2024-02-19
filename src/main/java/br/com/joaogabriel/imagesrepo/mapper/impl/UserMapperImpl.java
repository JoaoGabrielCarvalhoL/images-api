package br.com.joaogabriel.imagesrepo.mapper.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.joaogabriel.imagesrepo.entity.User;
import br.com.joaogabriel.imagesrepo.entity.UserInfo;
import br.com.joaogabriel.imagesrepo.mapper.UserMapper;
import br.com.joaogabriel.imagesrepo.payload.request.UserPostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.UserGenericResponse;
import br.com.joaogabriel.imagesrepo.payload.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserMapperImpl implements UserMapper {
	
	private final PasswordEncoder passwordEncoder;
	
	public UserMapperImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public User toUser(UserPostRequest userPostRequest, HttpServletRequest httpServletRequest) {
		if (httpServletRequest != null) {
			return new User(userPostRequest.name(), 
					userPostRequest.email(), 
					userPostRequest.username(), 
					this.passwordEncoder.encode(userPostRequest.hashPassword()), 
					new UserInfo(httpServletRequest.getHeader("User-Agent"),
                    httpServletRequest.getRemoteAddr(), 
                    httpServletRequest.getLocale().toString()));
		}
		return new User(userPostRequest.name(), 
				userPostRequest.email(), 
				userPostRequest.username(), 
				this.passwordEncoder.encode(userPostRequest.hashPassword()));
	}

	@Override
	public UserGenericResponse toUserGenericResponse(User user) {
		return new UserGenericResponse(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt());
	}

	@Override
	public UserResponse toUserResponse(User user) {
		return new UserResponse(user.getId(), user.getName(), user.getEmail(), 
				user.getUsername(), user.getUserInfo().getUserAgent(), user.getUserInfo().getInternetProtocol(), 
				user.getUserInfo().getLocale(), user.getCreatedAt());
	}

}
