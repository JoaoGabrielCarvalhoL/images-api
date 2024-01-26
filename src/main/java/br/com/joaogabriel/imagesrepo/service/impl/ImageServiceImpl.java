package br.com.joaogabriel.imagesrepo.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.joaogabriel.imagesrepo.entity.Image;
import br.com.joaogabriel.imagesrepo.exception.ResourceNotFoundException;
import br.com.joaogabriel.imagesrepo.mapper.ImageMapper;
import br.com.joaogabriel.imagesrepo.payload.request.ImagePostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.ImageResponse;
import br.com.joaogabriel.imagesrepo.repository.ImageRepository;
import br.com.joaogabriel.imagesrepo.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final ImageMapper imageMapper;
	private final ImageRepository imageRepository;
	
	public ImageServiceImpl(ImageMapper imageMapper, ImageRepository imageRepository) {
		this.imageMapper = imageMapper; 
		this.imageRepository = imageRepository;
	}

	@Override
	public ImageResponse save(ImagePostRequest image) {
		logger.info("Saving image into repository");
		Image saved = this.imageRepository.save(imageMapper.toImage(image));
		return imageMapper.toImageResponse(saved);
	}

	@Override
	public Image getById(UUID id) {
		logger.info("Getting image by id {}", id);
		return this.imageRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + id));
	}
}
