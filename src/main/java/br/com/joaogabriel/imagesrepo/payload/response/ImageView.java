package br.com.joaogabriel.imagesrepo.payload.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.joaogabriel.imagesrepo.enumerations.ImageExtension;

public class ImageView {
	private Long size; 
	private String name; 
	private ImageExtension extension;
	private UUID id;
	private String source;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime uploadIn;
	
	public ImageView(Long size, String name, ImageExtension extension, UUID id, String source, LocalDateTime uploadIn) {
		this.size = size; 
		this.name = name; 
		this.extension = extension; 
		this.id = id; 
		this.source = source;
		this.uploadIn = uploadIn;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageExtension getExtension() {
		return extension;
	}

	public void setExtension(ImageExtension extension) {
		this.extension = extension;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public LocalDateTime getUploadIn() {
		return uploadIn;
	}
	
	public void setUploadIn(LocalDateTime uploadIn) {
		this.uploadIn = uploadIn;
	}
	
	

}
