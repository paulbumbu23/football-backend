package com.Fotbal.football_backend.model.dto.image;

import lombok.Data;

@Data
public class ImageResponseDto {
    private String imageId;
    private byte[] content;
    private String extension;
}
