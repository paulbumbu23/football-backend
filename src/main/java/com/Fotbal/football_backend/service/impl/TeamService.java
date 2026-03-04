package com.Fotbal.football_backend.service.impl;

import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.converter.PlayerConverter;
import com.Fotbal.football_backend.model.converter.TeamConverter;
import com.Fotbal.football_backend.model.dto.player.PlayerResponseDto;
import com.Fotbal.football_backend.model.dto.team.ExtendedTeamResponseDto;
import com.Fotbal.football_backend.model.dto.team.FirstElevenResponseDto;
import com.Fotbal.football_backend.model.dto.team.TeamRequestDto;
import com.Fotbal.football_backend.model.dto.team.TeamResponseDto;
import com.Fotbal.football_backend.model.entity.Championship;
import com.Fotbal.football_backend.model.entity.Player;
import com.Fotbal.football_backend.model.entity.Team;
import com.Fotbal.football_backend.model.enums.Position;
import com.Fotbal.football_backend.repository.ChampionshipRepository;
import com.Fotbal.football_backend.repository.PlayerRepository;
import com.Fotbal.football_backend.repository.TeamRepository;
import com.Fotbal.football_backend.service.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService implements ITeamService {

    private final TeamRepository teamRepository;
    private final ChampionshipRepository championshipRepository;
    private final TeamConverter teamConverter;
    private final PlayerRepository playerRepository;
    private final PlayerConverter playerConverter;

    public List<TeamResponseDto> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(teamConverter::convertTeamToResponse)
                .collect(Collectors.toList());
    }

    public TeamResponseDto getTeamById(String teamId) throws NotFoundException {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            return teamConverter.convertTeamToResponse(team.get());
        }
        throw new NotFoundException((String.format("Team with id %s not found", teamId)));
    }

    public TeamResponseDto addTeam(TeamRequestDto teamRequestDto) throws NotFoundException {
        Optional<Championship> championship = championshipRepository.findChampionshipByName((teamRequestDto.getChampionshipName()));
        if (championship.isEmpty()) {
            throw new NotFoundException("Championship with name " + teamRequestDto.getChampionshipName() + " not found");
        }

        Team team = teamConverter.convertRequestToTeam(teamRequestDto);
        team.setChampionship(championship.get());
        Team savedTeam = teamRepository.save(team);
        return teamConverter.convertTeamToResponse(savedTeam);
    }

    public TeamResponseDto updateTeam(String teamId, TeamRequestDto teamRequestDto) throws NotFoundException {
        Optional<Championship> championship = championshipRepository.findChampionshipByName((teamRequestDto.getChampionshipName()));
        if (championship.isEmpty()) {
            throw new NotFoundException("Championship with name " + teamRequestDto.getChampionshipName() + " not found");
        }

        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            team.setFormation(teamRequestDto.getFormation());
            team.setName(teamRequestDto.getTeamName());
            team.setChampionship(championship.get());
            Team updatedTeam = teamRepository.save(team);
            return teamConverter.convertTeamToResponse(updatedTeam);
        }
        throw new NotFoundException((String.format("Team with id %s not found", teamId)));
    }

    public void deleteTeam(String teamId) throws NotFoundException {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new NotFoundException((String.format("Team with id %s not found", teamId)));
        }
        teamRepository.delete(team.get());
    }

    public ExtendedTeamResponseDto getFullTeam(String teamId) throws NotFoundException {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isPresent()) {
            List<Player> players = playerRepository.getPlayersByTeam_TeamId(teamId);
            ExtendedTeamResponseDto extendedTeamResponseDto = new ExtendedTeamResponseDto();
            extendedTeamResponseDto.setTeam(teamConverter.convertTeamToResponse(optionalTeam.get()));
            extendedTeamResponseDto.setPlayers(players.stream()
                    .map(playerConverter::convertPlayerToResponse)
                    .collect(Collectors.toList()));
            return extendedTeamResponseDto;
        }
        throw new NotFoundException((String.format("Team with id %s not found", teamId)));
    }

    public FirstElevenResponseDto getFirstEleven(String teamId) throws NotFoundException {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundException((String.format("Team with id %s not found", teamId))));

        List<Player> allPlayers = playerRepository.getPlayersByTeam_TeamId(teamId);

        String formationOfTeam = team.getFormation().getValue();
        String[] playersOnPosition = formationOfTeam.split("-");

        int defendersNumber = Integer.parseInt(playersOnPosition[0]);
        int midfieldersNumber = Integer.parseInt(playersOnPosition[1]);
        int attackersNumber = Integer.parseInt(playersOnPosition[2]);

        FirstElevenResponseDto response = new FirstElevenResponseDto();
        response.setTeam(teamConverter.convertTeamToResponse(team));

        List<PlayerResponseDto> goalkeepers = getTopPlayersByPosition(allPlayers, Position.GK, 1);
        if (!goalkeepers.isEmpty()) {
            response.setGoalkeeper(goalkeepers.get(0));
        }

        response.setDefenders(getTopPlayersByPosition(allPlayers, Position.DF, defendersNumber));
        response.setMidfielders(getTopPlayersByPosition(allPlayers, Position.MF, midfieldersNumber));
        response.setAttackers(getTopPlayersByPosition(allPlayers, Position.FW, attackersNumber));

        return response;
    }

    private List<PlayerResponseDto> getTopPlayersByPosition(List<Player> teamPlayers, Position position, int numberOfPlayersOnPosition) throws NotFoundException {
        if (teamPlayers.stream()
                .filter(player -> player.getPosition() == position)
                .collect(Collectors.toList()).size() < numberOfPlayersOnPosition) {
            throw new NotFoundException(String.format("Not enough players on position:", position));
        }

        return teamPlayers.stream()
                .filter(player -> player.getPosition() == position)
                .sorted(Comparator.comparingInt(Player::getOverall).reversed())
                .limit(numberOfPlayersOnPosition)
                .map(playerConverter::convertPlayerToResponse)
                .collect(Collectors.toList());
    }
}
