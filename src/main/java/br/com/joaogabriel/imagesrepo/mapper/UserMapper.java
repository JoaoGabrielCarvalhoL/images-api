package br.com.joaogabriel.imagesrepo.mapper;

import br.com.joaogabriel.imagesrepo.entity.User;
import br.com.joaogabriel.imagesrepo.payload.request.UserPostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.UserGenericResponse;
import br.com.joaogabriel.imagesrepo.payload.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface UserMapper {
	
	User toUser(UserPostRequest userPostRequest, 
			HttpServletRequest httpServletRequest);
	
	UserGenericResponse toUserGenericResponse(User user);
	
	UserResponse toUserResponse(User user);

}
