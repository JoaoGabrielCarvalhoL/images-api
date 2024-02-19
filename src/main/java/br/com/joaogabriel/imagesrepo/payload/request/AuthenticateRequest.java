package br.com.joaogabriel.imagesrepo.payload.request;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticateRequest(
		
		@NotBlank(message = "The field email cannot be empty!")
        @Email(regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
		String email, 

        @NotBlank(message = "The field password cannot be empty!")
		String password) implements Serializable {

}
