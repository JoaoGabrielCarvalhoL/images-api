package br.com.joaogabriel.imagesrepo.service;

import br.com.joaogabriel.imagesrepo.jwt.response.TokenResponse;
import br.com.joaogabriel.imagesrepo.payload.request.AuthenticateRequest;

public interface AuthenticationService {
	
	TokenResponse authenticate(final AuthenticateRequest authenticateRequest);

}
