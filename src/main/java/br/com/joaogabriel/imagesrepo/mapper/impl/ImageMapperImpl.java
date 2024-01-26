package br.com.joaogabriel.imagesrepo.mapper.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.joaogabriel.imagesrepo.entity.Image;
import br.com.joaogabriel.imagesrepo.enumerations.ImageExtension;
import br.com.joaogabriel.imagesrepo.mapper.ImageMapper;
import br.com.joaogabriel.imagesrepo.payload.request.ImagePostRequest;
import br.com.joaogabriel.imagesrepo.payload.response.ImageResponse;

@Service
public class ImageMapperImpl implements ImageMapper {

	@Override
	public ImageResponse toImageResponse(Image image) {
		return new ImageResponse(image.getId(), image.getName(), image.getExtension(), image.getSize(), 
				image.getUploadIn());
	}

	@Override
	public Image toImage(ImagePostRequest image) {
		return new Image(image.name(), image.size(), image.extension(), 
				String.join(",", image.tags()), image.file());
	}

	@Override
	public ImagePostRequest toImagePostRequest(MultipartFile file, String name, List<String> tags) throws IOException {
		return new ImagePostRequest(name, file.getSize(), 
				ImageExtension.valueOf(MediaType.valueOf(file.getContentType())), 
				tags, file.getBytes());
		
	}

}
