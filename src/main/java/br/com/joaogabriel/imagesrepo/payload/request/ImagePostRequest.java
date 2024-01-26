package br.com.joaogabriel.imagesrepo.payload.request;

import java.io.Serializable;
import java.util.List;

import br.com.joaogabriel.imagesrepo.enumerations.ImageExtension;

public record ImagePostRequest(
		String name,
		Long size,
		ImageExtension extension,
		List<String> tags,
		byte[] file
		) implements Serializable{

	
}
