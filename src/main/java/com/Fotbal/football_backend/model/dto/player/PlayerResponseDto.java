package com.Fotbal.football_backend.model.dto.player;

import com.Fotbal.football_backend.model.dto.image.ImageResponseDto;
import com.Fotbal.football_backend.model.enums.Position;
import lombok.Data;

@Data
public class PlayerResponseDto {
    private String id;
    private String firstName;
    private String lastName;
    private int overall;
    private int numberOnShirt;
    private Position position;
    private String teamName;
    private ImageResponseDto profileImage;
}
