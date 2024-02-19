package br.com.joaogabriel.imagesrepo.exception;

import java.io.Serial;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyUsedException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 0L;

	public ResourceAlreadyUsedException(String message) {
		super(message);
	}
}
