package br.com.joaogabriel.imagesrepo.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.joaogabriel.imagesrepo.entity.Image;
import br.com.joaogabriel.imagesrepo.enumerations.ImageExtension;
import br.com.joaogabriel.imagesrepo.exception.ResourceNotFoundException;
import br.com.joaogabriel.imagesrepo.mapper.ImageMapper;
import br.com.joaogabriel.imagesrepo.payload.request.ImagePostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.DocumentViewResponse;
import br.com.joaogabriel.imagesrepo.payload.response.ImageResponse;
import br.com.joaogabriel.imagesrepo.payload.response.ImageView;
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

	@Override
	public List<DocumentViewResponse> findByExtension(ImageExtension extension) {
		logger.info("Getting all documents with extension: {}", extension);
		return this.imageRepository.findByExtension(extension).stream().map(imageMapper::toDocumentViewResponse)
				.toList();
	}

	@Override
	public DocumentViewResponse findByName(String parameter) {
		logger.info("Getting document with name: {}", parameter);
		Image document = this.imageRepository.findByName(parameter);
		return imageMapper.toDocumentViewResponse(document);
	}

	@Override
	public List<ImageView> findAll() {
		logger.info("Getting all documents");
		return this.imageRepository.findAll().stream().map(imageMapper::toImageView).toList();
	}

	@Override
	public DocumentViewResponse findById(UUID id) {
		logger.info("Find document by id: {}", id);
		return this.imageRepository.findById(id).map(imageMapper::toDocumentViewResponse)
				.orElseThrow(() -> new ResourceNotFoundException("Document not found! Id: " + id));
	}

	@Override
	public List<ImageView> findByExtensionView(ImageExtension extension) {
		logger.info("Getting all documents by extension: {}", extension);
		return this.imageRepository.findByExtension(extension).stream().map(imageMapper::toImageView).toList();
	}

	@Override
	public ImageView findByNameView(String name) {
		logger.info("Getting document by name: {}", name);
		Image image = this.imageRepository.findByName(name);
		if (image == null) {
			throw new ResourceNotFoundException("Document not found into database. Name: " + name);
		}
		return imageMapper.toImageView(image);
	}
}
