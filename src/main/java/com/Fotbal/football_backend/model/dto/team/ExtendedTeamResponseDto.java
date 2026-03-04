package com.Fotbal.football_backend.model.dto.team;

import com.Fotbal.football_backend.model.dto.player.PlayerResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class ExtendedTeamResponseDto {
    private TeamResponseDto team;
    List<PlayerResponseDto> players;
}
