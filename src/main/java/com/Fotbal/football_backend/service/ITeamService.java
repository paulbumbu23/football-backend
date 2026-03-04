package com.Fotbal.football_backend.service;

import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.dto.team.ExtendedTeamResponseDto;
import com.Fotbal.football_backend.model.dto.team.FirstElevenResponseDto;
import com.Fotbal.football_backend.model.dto.team.TeamRequestDto;
import com.Fotbal.football_backend.model.dto.team.TeamResponseDto;

import java.util.List;

public interface ITeamService {
    List<TeamResponseDto> getAllTeams();

    TeamResponseDto getTeamById(String Id) throws NotFoundException;

    TeamResponseDto addTeam(TeamRequestDto teamRequestDto) throws NotFoundException;

    TeamResponseDto updateTeam(String teamId, TeamRequestDto teamRequestDto) throws NotFoundException;

    void deleteTeam(String teamId) throws NotFoundException;

    ExtendedTeamResponseDto getFullTeam(String teamId) throws NotFoundException;

    FirstElevenResponseDto getFirstEleven(String teamId) throws NotFoundException;

}

