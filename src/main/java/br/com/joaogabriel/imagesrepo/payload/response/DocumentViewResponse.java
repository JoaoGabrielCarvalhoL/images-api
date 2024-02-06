package br.com.joaogabriel.imagesrepo.payload.response;

import java.io.Serializable;
import java.util.UUID;

import br.com.joaogabriel.imagesrepo.enumerations.ImageExtension;

public record DocumentViewResponse(byte[] file, Long size, String name, ImageExtension extension,
		UUID id) implements Serializable {

}
