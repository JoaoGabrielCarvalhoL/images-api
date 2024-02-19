package br.com.joaogabriel.imagesrepo.payload.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPostRequest(
		@NotBlank(message = "The field name cannot be empty!") @Size(min = 5, max = 200)
		String name, 
		
		@NotBlank(message = "The field email cannot be empty!")
        @Email(regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
		String email, 
		
		@NotBlank(message = "The field username cannot be empty!") @Size(min = 5, max = 200)
		String username, 
		
		@JsonProperty("password")
        @NotBlank(message = "The field password cannot be empty!")
		String hashPassword
		) implements Serializable {

}
