package com.Fotbal.football_backend.model.dto.team;

import com.Fotbal.football_backend.model.dto.player.PlayerResponseDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({ "team", "goalkeeper", "defenders", "midfielders", "attackers" })
public class FirstElevenResponseDto {
    private TeamResponseDto team;
    private PlayerResponseDto goalkeeper;
    private List<PlayerResponseDto> defenders;
    private List<PlayerResponseDto> midfielders;
    private List<PlayerResponseDto> attackers;
}
