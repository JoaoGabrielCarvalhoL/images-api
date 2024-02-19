package br.com.joaogabriel.imagesrepo.exception;

public class InvalidTokenException extends RuntimeException {
	
	private static final long serialVersionUID = 0L;
	
	public InvalidTokenException(String message) {
		super(message);
	}

}
