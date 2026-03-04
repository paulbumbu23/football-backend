package com.Fotbal.football_backend.service.impl;

import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.converter.PlayerConverter;
import com.Fotbal.football_backend.model.converter.TeamConverter;
import com.Fotbal.football_backend.model.dto.player.PlayerRequestDto;
import com.Fotbal.football_backend.model.dto.player.PlayerResponseDto;
import com.Fotbal.football_backend.model.dto.team.TeamFilterRequestDto;
import com.Fotbal.football_backend.model.entity.Player;
import com.Fotbal.football_backend.model.entity.Team;
import com.Fotbal.football_backend.repository.PlayerRepository;
import com.Fotbal.football_backend.repository.TeamRepository;
import com.Fotbal.football_backend.service.IPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerService implements IPlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerConverter playerConverter;
    private final TeamConverter teamConverter;

    public List<PlayerResponseDto> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(playerConverter::convertPlayerToResponse)
                .collect(Collectors.toList());
    }

    public PlayerResponseDto getPlayerById(String playerId) throws NotFoundException {
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isPresent()) {
            return playerConverter.convertPlayerToResponse(player.get());
        }
        throw new NotFoundException(String.format("Player with id %s not found", playerId));
    }

    public PlayerResponseDto updatePlayerById(String playerId, PlayerRequestDto playerRequestDto) throws NotFoundException {
        Optional<Team> optionalTeam = teamRepository.findByName(playerRequestDto.getTeamName());
        if (optionalTeam.isEmpty()) {
            throw new NotFoundException(String.format("Team with name %s not found", playerRequestDto.getTeamName()));
        }

        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.setFirstName(playerRequestDto.getFirstName());
            player.setLastName(playerRequestDto.getLastName());
            player.setOverall(playerRequestDto.getOverall());
            player.setPosition(playerRequestDto.getPosition());
            player.setNumberOnShirt(playerRequestDto.getNumberOnShirt());
            player.setTeam(optionalTeam.get());
            Player updatedPlayer = playerRepository.save(player);
            return playerConverter.convertPlayerToResponse(updatedPlayer);
        }
        throw new NotFoundException(String.format("Player with id %s not found", playerId));
    }

    public PlayerResponseDto addPlayer(PlayerRequestDto playerRequestDto) throws NotFoundException {
        Optional<Team> optionalTeam = teamRepository.findByName(playerRequestDto.getTeamName());
        if (optionalTeam.isEmpty()) {
            throw new NotFoundException(String.format("Team with name %s not found", playerRequestDto.getTeamName()));
        }
        Player player = playerConverter.convertRequestToPlayer(playerRequestDto);
        player.setTeam(optionalTeam.get());
        Player savedPlayer = playerRepository.save(player);
        return playerConverter.convertPlayerToResponse(savedPlayer);
    }

    public void deletePlayerById(String playerId) throws NotFoundException {
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isEmpty()) {
            throw new NotFoundException(String.format("Player with id %s not found", playerId));
        }
        playerRepository.deleteById(playerId);

    }

    public List<PlayerResponseDto> getPlayersByTeamName(TeamFilterRequestDto teamFilterRequestDto) {
        String teamName = teamFilterRequestDto.getTeamName();
        List<Player> players = playerRepository.getPlayersByTeam_Name(teamName);
        return players.stream()
                .map(playerConverter::convertPlayerToResponse)
                .collect(Collectors.toList());
    }


}
