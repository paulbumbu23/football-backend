package com.Fotbal.football_backend.service.impl;

import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.converter.ImageConverter;
import com.Fotbal.football_backend.model.converter.PlayerConverter;
import com.Fotbal.football_backend.model.converter.TeamConverter;
import com.Fotbal.football_backend.model.dto.image.ImageResponseDto;
import com.Fotbal.football_backend.model.dto.player.PlayerResponseDto;
import com.Fotbal.football_backend.model.dto.team.TeamResponseDto;
import com.Fotbal.football_backend.model.entity.Image;
import com.Fotbal.football_backend.model.entity.Player;
import com.Fotbal.football_backend.model.entity.Team;
import com.Fotbal.football_backend.repository.ImageRepository;
import com.Fotbal.football_backend.repository.PlayerRepository;
import com.Fotbal.football_backend.repository.TeamRepository;
import com.Fotbal.football_backend.service.IImageService;
import com.Fotbal.football_backend.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final PlayerRepository playerRepository;
    private final ImageRepository imageRepository;
    private final TeamRepository teamRepository;
    private final ImageUtils imageUtils;
    private final ImageConverter imageConverter;
    private final PlayerConverter playerConverter;
    private final TeamConverter teamConverter;

    @Override
    public List<String> getImagesIds() {
        return imageRepository.findAll().stream()
                .map(image -> image.getImageId())
                .toList();
    }

    @Override
    public ImageResponseDto getImage(String imageId) throws NotFoundException {
        Optional<Image> image = imageRepository.getImageByImageId(imageId);

        if (image.isEmpty()) {
            throw new NotFoundException(String.format("Image with id %s not found", imageId));
        }

        return imageConverter.convertImage(image.get());
    }

    @Override
    public ImageResponseDto uploadImage(MultipartFile file) throws IOException {
        Image image = imageUtils.createNewImage(file);
        Image savedImage = imageRepository.save(image);
        return imageConverter.convertImage(savedImage);
    }

    @Override
    public PlayerResponseDto uploadPlayerImage(String playerId, MultipartFile file) throws NotFoundException, IOException {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isEmpty()) {
            throw new NotFoundException(String.format("Player with id %s not found", playerId));
        }

        Image image = imageUtils.createNewImage(file);
        Image savedImage = imageRepository.save(image);
        Player player = optionalPlayer.get();
        player.setProfileImage(savedImage);
        Player updatedPlayer = playerRepository.save(player);

        return playerConverter.convertPlayerToResponse(updatedPlayer);
    }

    @Override
    public TeamResponseDto uploadTeamLogo(String teamId, MultipartFile file) throws NotFoundException, IOException {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isEmpty()) {
            throw new NotFoundException(String.format("Team with id %s not found", teamId));
        }

        Image image = imageUtils.createNewImage(file);
        Image savedImage = imageRepository.save(image);
        Team team = optionalTeam.get();
        team.setLogoImage(savedImage);
        Team updatedTeam = teamRepository.save(team);

        return teamConverter.convertTeamToResponse(updatedTeam);
    }

}
