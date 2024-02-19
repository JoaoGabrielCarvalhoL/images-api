package br.com.joaogabriel.imagesrepo.jwt.response;

import java.io.Serializable;

public record TokenResponse(String token) implements Serializable {

}
