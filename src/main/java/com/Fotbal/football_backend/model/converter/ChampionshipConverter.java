package com.Fotbal.football_backend.model.converter;

import com.Fotbal.football_backend.model.dto.championship.ChampionshipRequestDto;
import com.Fotbal.football_backend.model.dto.championship.ChampionshipResponseDto;
import com.Fotbal.football_backend.model.dto.team.TeamResponseDto;
import com.Fotbal.football_backend.model.entity.Championship;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ChampionshipConverter {

    private TeamConverter teamConverter;

    public ChampionshipResponseDto convertChampionshipToResponseDto(Championship championship) {
        ChampionshipResponseDto responseDto = new ChampionshipResponseDto();
        responseDto.setChampionshipId(championship.getChampionshipId());
        responseDto.setChampionshipName(championship.getName());
        responseDto.setChampionshipCountry(championship.getCountry());
        if (championship.getTeams() != null) {
            List<TeamResponseDto> teamDtos = championship.getTeams().stream()
                    .map(teamConverter::convertTeamToResponse)
                    .collect(Collectors.toList());

            responseDto.setTeams(teamDtos);
        }
        return responseDto;
    }

    public Championship convertRequestToChampionship(ChampionshipRequestDto championshipRequestDto) {
        Championship championship = new Championship();
        championship.setName(championshipRequestDto.getChampionshipName());
        championship.setCountry(championshipRequestDto.getChampionshipCountry());
        return championship;
    }
}