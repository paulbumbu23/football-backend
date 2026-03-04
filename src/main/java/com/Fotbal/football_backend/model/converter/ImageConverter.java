package com.Fotbal.football_backend.model.converter;

import com.Fotbal.football_backend.model.dto.image.ImageResponseDto;
import com.Fotbal.football_backend.model.entity.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter {

    public ImageResponseDto convertImage(Image image) {
        ImageResponseDto imageResponseDto = new ImageResponseDto();
        imageResponseDto.setImageId(image.getImageId());
        imageResponseDto.setExtension(image.getExtension());
        imageResponseDto.setContent(image.getContent());
        return imageResponseDto;
    }
}
