package com.Fotbal.football_backend.model.converter;


import com.Fotbal.football_backend.model.dto.image.ImageResponseDto;
import com.Fotbal.football_backend.model.dto.team.TeamRequestDto;
import com.Fotbal.football_backend.model.dto.team.TeamResponseDto;
import com.Fotbal.football_backend.model.entity.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeamConverter {

    private ImageConverter imageConverter;

    public TeamResponseDto convertTeamToResponse(Team team) {
        TeamResponseDto teamResponseDto = new TeamResponseDto();
        teamResponseDto.setId(team.getTeamId());
        teamResponseDto.setTeamName(team.getName());
        teamResponseDto.setFormation(team.getFormation());
        if (team.getLogoImage() != null) {
            ImageResponseDto imageResponseDto = imageConverter.convertImage(team.getLogoImage());
            teamResponseDto.setTeamLogo(imageResponseDto);
        }
        return teamResponseDto;
    }

    public Team convertRequestToTeam(TeamRequestDto teamRequestDto) {
        Team team = new Team();
        team.setName(teamRequestDto.getTeamName());
        team.setFormation(teamRequestDto.getFormation());
        return team;
    }
}



