package br.com.joaogabriel.imagesrepo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogabriel.imagesrepo.jwt.response.TokenResponse;
import br.com.joaogabriel.imagesrepo.payload.request.AuthenticateRequest;
import br.com.joaogabriel.imagesrepo.service.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	 
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TokenResponse> authentication(@RequestBody @Valid AuthenticateRequest authenticateRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.authenticationService.authenticate(authenticateRequest));
	}

}
