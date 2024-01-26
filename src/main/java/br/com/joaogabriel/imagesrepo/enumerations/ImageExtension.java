package br.com.joaogabriel.imagesrepo.enumerations;

import java.util.Arrays;

import org.springframework.http.MediaType;


public enum ImageExtension {
	PNG(MediaType.IMAGE_PNG), 
	GIF(MediaType.IMAGE_GIF), 
	JPEG(MediaType.IMAGE_JPEG),
	PDF(MediaType.APPLICATION_PDF);
	
	private MediaType mediaType;
	
	ImageExtension(MediaType mediaType) {
		this.mediaType = mediaType;
	}
	
	public MediaType getMediaType() { 
		return this.mediaType;
	}
	
	
	public static ImageExtension valueOf(MediaType mediaType) {
		if(mediaType == null) return null;
		return Arrays.stream(values())
				.filter(extension -> extension.mediaType.equals(mediaType))
				.findFirst().orElse(null);
	}
}
