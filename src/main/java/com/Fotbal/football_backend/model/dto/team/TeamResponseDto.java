package com.Fotbal.football_backend.model.dto.team;

import com.Fotbal.football_backend.model.dto.image.ImageResponseDto;
import com.Fotbal.football_backend.model.enums.Formation;
import lombok.Data;

@Data
public class TeamResponseDto {
    private String id;
    private String teamName;
    private Formation formation;
    private ImageResponseDto teamLogo;
}
