package br.com.joaogabriel.imagesrepo.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.joaogabriel.imagesrepo.entity.Image;
import br.com.joaogabriel.imagesrepo.mapper.ImageMapper;
import br.com.joaogabriel.imagesrepo.payload.request.ImagePostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.ImageResponse;
import br.com.joaogabriel.imagesrepo.service.ImageService;

@RestController
@RequestMapping("/v1/images")
public class ImageController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final ImageService imageService;
	private final ImageMapper imageMapper;

	public ImageController(ImageService imageService, ImageMapper imageMapper) {
		this.imageService = imageService;
		this.imageMapper = imageMapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ImageResponse> save(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("tags") List<String> tags) throws IOException {
		logger.info("Request accept. Saving image - name: {} - size: {}", file.getOriginalFilename(), file.getSize());
		
		ImagePostRequest image = imageMapper.toImagePostRequest(file, name, tags);
		ImageResponse saved = this.imageService.save(image);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saved.id()).toUri();
		
		return ResponseEntity.created(location).body(saved);
		
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<byte[]> getImage(@PathVariable("id") UUID id) {
		Image image = this.imageService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(image.getExtension().getMediaType());
		headers.setContentLength(image.getSize());
		headers.setContentDispositionFormData("inline; filename=\"" + image.getOriginalFileName() + "\"", 
				image.getOriginalFileName());
		/**
		 * headers.setContentDispositionFormData("attachment; filename=\"" + image.getOriginalFileName() + "\"", 
				image.getOriginalFileName());
		 * */
		return new ResponseEntity<byte[]>(image.getFile(), headers, HttpStatus.OK);
	}
}
//ImageExtension.valueOf(StringUtils.getExtension(file.getOriginalFilename()))