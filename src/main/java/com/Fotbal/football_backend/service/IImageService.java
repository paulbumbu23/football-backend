package com.Fotbal.football_backend.service;

import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.dto.image.ImageResponseDto;
import com.Fotbal.football_backend.model.dto.player.PlayerResponseDto;
import com.Fotbal.football_backend.model.dto.team.TeamResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {

    List<String> getImagesIds();

    ImageResponseDto getImage(String imageId) throws NotFoundException;

    ImageResponseDto uploadImage(MultipartFile file) throws IOException;

    PlayerResponseDto uploadPlayerImage(String playerId, MultipartFile file) throws NotFoundException, IOException;

    TeamResponseDto uploadTeamLogo(String teamId, MultipartFile file) throws NotFoundException, IOException;
}
