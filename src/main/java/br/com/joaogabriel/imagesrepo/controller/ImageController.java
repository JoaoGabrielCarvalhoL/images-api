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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import br.com.joaogabriel.imagesrepo.enumerations.ImageExtension;
import br.com.joaogabriel.imagesrepo.mapper.ImageMapper;
import br.com.joaogabriel.imagesrepo.payload.request.ImagePostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.DocumentViewResponse;
import br.com.joaogabriel.imagesrepo.payload.response.ImageResponse;
import br.com.joaogabriel.imagesrepo.payload.response.ImageView;
import br.com.joaogabriel.imagesrepo.service.ImageService;

@RestController
@RequestMapping("/v1/images")
@CrossOrigin(origins = "http://localhost:3000")
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
	
	@GetMapping("/filter/extension")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<DocumentViewResponse>> findByExtension(@RequestParam("extension") ImageExtension extension) {
		List<DocumentViewResponse> documents = this.imageService.findByExtension(extension);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<List<DocumentViewResponse>>(documents, headers, HttpStatus.OK);
	}
	
	@GetMapping("/filter/name")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<byte[]> findByName(@RequestParam("parameter") String parameter) {
		DocumentViewResponse documentViewResponse = this.imageService.findByName(parameter);
		String originalFileName = String.format("%s.%s", documentViewResponse.name(), 
				documentViewResponse.extension().name().toLowerCase());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(documentViewResponse.extension().getMediaType());
		headers.setContentLength(documentViewResponse.size());
		headers.setContentDispositionFormData("attachment; filename=\"" + originalFileName 
				+  "" + originalFileName + "\"", parameter);
		return new ResponseEntity<byte[]>(documentViewResponse.file(), headers, HttpStatus.OK);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ImageView>> findAll() {
		List<ImageView> result = this.imageService.findAll();
		addImageUri(result);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping("/v2/filter/extension")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ImageView>> findByExtensionView(@RequestParam("extension") ImageExtension extension) {
		List<ImageView> result = this.imageService.findByExtensionView(extension);
		addImageUri(result);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping("/v2/filter/name")
	public ResponseEntity<ImageView> findByNameView(@RequestParam("name") String name) {
		ImageView imageView = this.imageService.findByNameView(name);
		imageView.setSource(buildUri(imageView.getId()).toString());
		return ResponseEntity.status(HttpStatus.OK).body(imageView);
	}
	
	private static URI buildUri(UUID id) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
	}
	
	private void addImageUri(List<ImageView> documents) {
		for (ImageView list : documents) {
			list.setSource(buildUri(list.getId()).toString());
		}
	}
}
//ImageExtension.valueOf(StringUtils.getExtension(file.getOriginalFilename()))