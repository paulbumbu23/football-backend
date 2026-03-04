package com.Fotbal.football_backend.model.dto.championship;

import com.Fotbal.football_backend.model.dto.team.TeamResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class ChampionshipResponseDto {
    private String championshipId;
    private String championshipName;
    private String championshipCountry;
    private List<TeamResponseDto> teams;
}
