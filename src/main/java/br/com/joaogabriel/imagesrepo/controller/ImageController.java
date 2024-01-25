package br.com.joaogabriel.imagesrepo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/images")
public class ImageController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public ImageController() {

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> save(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("tags") List<String> tags) {
		logger.info("Request accept. Saving image - name: {} - size: {}", file.getOriginalFilename(), file.getSize());
		return null;
	}
}
