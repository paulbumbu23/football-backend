package com.Fotbal.football_backend.service;

import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.dto.player.PlayerRequestDto;
import com.Fotbal.football_backend.model.dto.player.PlayerResponseDto;
import com.Fotbal.football_backend.model.dto.team.TeamFilterRequestDto;
import com.Fotbal.football_backend.model.dto.team.TeamRequestDto;
import com.Fotbal.football_backend.model.dto.team.TeamResponseDto;

import java.util.List;

public interface IPlayerService {
    List<PlayerResponseDto> getAllPlayers();

    PlayerResponseDto getPlayerById(String playerId) throws NotFoundException;

    PlayerResponseDto addPlayer(PlayerRequestDto playerRequestDto) throws NotFoundException;

    PlayerResponseDto updatePlayerById(String playerId, PlayerRequestDto playerRequestDto) throws NotFoundException;

    void deletePlayerById(String playerId) throws NotFoundException;

    List<PlayerResponseDto> getPlayersByTeamName(TeamFilterRequestDto teamFilterRequestDto) throws NotFoundException;

}
