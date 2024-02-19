package br.com.joaogabriel.imagesrepo.payload.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
		UUID id, 
		String name, 
		String email,
		String username,
		@JsonProperty("Browser")
		String userAgent, 
		@JsonProperty("IP")
		String internetProtocol, 
		@JsonProperty("Locale")
		String locale,
		LocalDateTime createdAt
		) implements Serializable {

}
