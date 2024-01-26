package br.com.joaogabriel.imagesrepo.service;

import java.util.UUID;

import br.com.joaogabriel.imagesrepo.entity.Image;
import br.com.joaogabriel.imagesrepo.payload.request.ImagePostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.ImageResponse;

public interface ImageService {
	
	ImageResponse save(final ImagePostRequest image);
	
	Image getById(final UUID id);

}
