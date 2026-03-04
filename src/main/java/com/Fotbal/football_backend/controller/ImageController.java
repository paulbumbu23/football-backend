package com.Fotbal.football_backend.controller;


import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.dto.image.ImageResponseDto;
import com.Fotbal.football_backend.model.dto.player.PlayerResponseDto;
import com.Fotbal.football_backend.model.dto.team.TeamResponseDto;
import com.Fotbal.football_backend.service.IImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ImageController {

    private final IImageService imageService;

    @GetMapping("/images")
    public ResponseEntity<List<String>> getAllImagesIds() {
        return ResponseEntity.ok().body(imageService.getImagesIds());
    }

    @GetMapping("/images/{imageId}")
    public ResponseEntity<ImageResponseDto> getImage(@PathVariable String imageId) throws NotFoundException {
        return ResponseEntity.ok().body(imageService.getImage(imageId));
    }

    @PostMapping("/images")
    public ResponseEntity<ImageResponseDto> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(imageService.uploadImage(file));
    }

    @PostMapping("/images/players/{playerId}")
    public ResponseEntity<PlayerResponseDto> uploadPlayerImage(@PathVariable String playerId, @RequestParam("file") MultipartFile file) throws IOException, NotFoundException {
        return ResponseEntity.ok(imageService.uploadPlayerImage(playerId, file));
    }

    @PostMapping("/images/teams/{teamId}")
    public ResponseEntity<TeamResponseDto> uploadTeamImage(@PathVariable String teamId, @RequestParam("file") MultipartFile file) throws IOException, NotFoundException {
        return ResponseEntity.ok(imageService.uploadTeamLogo(teamId, file));
    }
}
