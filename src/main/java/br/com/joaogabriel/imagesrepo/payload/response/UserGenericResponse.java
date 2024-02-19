package br.com.joaogabriel.imagesrepo.payload.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserGenericResponse(
		UUID id, 
		String name, 
		String email,
		LocalDateTime createdAt
		) implements Serializable {

}
