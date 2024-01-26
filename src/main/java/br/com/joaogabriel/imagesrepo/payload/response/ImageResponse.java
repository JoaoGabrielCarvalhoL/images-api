package br.com.joaogabriel.imagesrepo.payload.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.joaogabriel.imagesrepo.enumerations.ImageExtension;

public record ImageResponse(
		UUID id, 
		String name, 
		ImageExtension extension,
		Long size,
		LocalDateTime uploadIn
		) implements Serializable {

}
