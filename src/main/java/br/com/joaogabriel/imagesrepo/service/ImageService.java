package br.com.joaogabriel.imagesrepo.service;

import java.util.List;
import java.util.UUID;

import br.com.joaogabriel.imagesrepo.entity.Image;
import br.com.joaogabriel.imagesrepo.enumerations.ImageExtension;
import br.com.joaogabriel.imagesrepo.payload.request.ImagePostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.DocumentViewResponse;
import br.com.joaogabriel.imagesrepo.payload.response.ImageResponse;
import br.com.joaogabriel.imagesrepo.payload.response.ImageView;

public interface ImageService {
	
	ImageResponse save(final ImagePostRequest image);
	
	Image getById(final UUID id);
	
	List<DocumentViewResponse> findByExtension(ImageExtension extension);
	
	List<ImageView> findByExtensionView(ImageExtension extension);
	
	ImageView findByNameView(String name);
	
	DocumentViewResponse findByName(String parameter);
	
	DocumentViewResponse findById(UUID id);
	
	List<ImageView> findAll();

}
