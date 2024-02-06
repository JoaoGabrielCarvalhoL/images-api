package br.com.joaogabriel.imagesrepo.mapper;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.joaogabriel.imagesrepo.entity.Image;
import br.com.joaogabriel.imagesrepo.payload.request.ImagePostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.DocumentViewResponse;
import br.com.joaogabriel.imagesrepo.payload.response.ImageResponse;
import br.com.joaogabriel.imagesrepo.payload.response.ImageView;

public interface ImageMapper {

	ImageResponse toImageResponse(Image image);
	
	Image toImage(ImagePostRequest imagePostRequest);
	
	ImagePostRequest toImagePostRequest(MultipartFile file, String name, List<String> tags) throws IOException;
	
	DocumentViewResponse toDocumentViewResponse(Image image);
	
	ImageView toImageView(Image image);
}
