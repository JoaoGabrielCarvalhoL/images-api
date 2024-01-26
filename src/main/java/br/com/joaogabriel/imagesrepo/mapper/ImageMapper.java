package br.com.joaogabriel.imagesrepo.mapper;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.joaogabriel.imagesrepo.entity.Image;
import br.com.joaogabriel.imagesrepo.payload.request.ImagePostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.ImageResponse;

public interface ImageMapper {

	ImageResponse toImageResponse(Image image);
	
	Image toImage(ImagePostRequest imagePostRequest);
	
	ImagePostRequest toImagePostRequest(MultipartFile file, String name, List<String> tags) throws IOException;
}
