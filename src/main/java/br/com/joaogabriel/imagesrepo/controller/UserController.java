package br.com.joaogabriel.imagesrepo.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.joaogabriel.imagesrepo.payload.request.UserPostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.UserGenericResponse;
import br.com.joaogabriel.imagesrepo.payload.response.UserResponse;
import br.com.joaogabriel.imagesrepo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UserGenericResponse> save(@RequestBody @Valid UserPostRequest postRequest, HttpServletRequest httpServletRequest) {
		UserGenericResponse response = this.userService.save(postRequest, httpServletRequest);
		return ResponseEntity.created(buildUri(response.id())).body(response);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserResponse> findById(@PathVariable("id") UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.userService.findById(id));
	}
	
	private URI buildUri(UUID id) {
		return ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
	}

}
