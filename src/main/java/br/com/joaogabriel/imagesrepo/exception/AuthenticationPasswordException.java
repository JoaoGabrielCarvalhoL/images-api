package br.com.joaogabriel.imagesrepo.exception;

import java.io.Serial;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthenticationPasswordException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 0L;
	
	public AuthenticationPasswordException(String message) {
		super(message);
	}

}
