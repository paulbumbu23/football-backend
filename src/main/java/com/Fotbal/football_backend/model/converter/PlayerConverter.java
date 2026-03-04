package com.Fotbal.football_backend.model.converter;


import com.Fotbal.football_backend.model.dto.image.ImageResponseDto;
import com.Fotbal.football_backend.model.dto.player.PlayerRequestDto;
import com.Fotbal.football_backend.model.dto.player.PlayerResponseDto;
import com.Fotbal.football_backend.model.entity.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlayerConverter {

    private final ImageConverter imageConverter;

    public PlayerResponseDto convertPlayerToResponse(Player player) {
        PlayerResponseDto playerResponseDto = new PlayerResponseDto();
        playerResponseDto.setId(player.getPlayerId());
        playerResponseDto.setFirstName(player.getFirstName());
        playerResponseDto.setLastName(player.getLastName());
        playerResponseDto.setPosition(player.getPosition());
        playerResponseDto.setOverall(player.getOverall());
        playerResponseDto.setNumberOnShirt(player.getNumberOnShirt());
        playerResponseDto.setTeamName(player.getTeam().getName());
        if (player.getProfileImage() != null) {
            ImageResponseDto imageDto = imageConverter.convertImage(player.getProfileImage());
            playerResponseDto.setProfileImage(imageDto);
        }
        return playerResponseDto;
    }

    public Player convertRequestToPlayer(PlayerRequestDto playerRequestDto) {
        Player player = new Player();
        player.setFirstName(playerRequestDto.getFirstName());
        player.setLastName(playerRequestDto.getLastName());
        player.setOverall(playerRequestDto.getOverall());
        player.setPosition(playerRequestDto.getPosition());
        player.setNumberOnShirt(playerRequestDto.getNumberOnShirt());
        return player;
    }
}
