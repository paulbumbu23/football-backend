package com.Fotbal.football_backend.controller;


import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.dto.team.ExtendedTeamResponseDto;
import com.Fotbal.football_backend.model.dto.team.FirstElevenResponseDto;
import com.Fotbal.football_backend.model.dto.team.TeamRequestDto;
import com.Fotbal.football_backend.model.dto.team.TeamResponseDto;
import com.Fotbal.football_backend.service.ITeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TeamController {

    // GET /teams/:teamId/squad -> FirstElevenTeamResponseDto

    private final ITeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<List<TeamResponseDto>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("/teams/{teamId}")
    public ResponseEntity<TeamResponseDto> getTeamById(@PathVariable String teamId) throws NotFoundException {
        return ResponseEntity.ok(teamService.getTeamById(teamId));
    }

    @PostMapping("/teams")
    public ResponseEntity<TeamResponseDto> addTeam(@RequestBody TeamRequestDto teamRequestDto) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.addTeam(teamRequestDto));
    }

    @PutMapping("/teams/{teamId}")
    public ResponseEntity<TeamResponseDto> updateTeam(@PathVariable String teamId, @RequestBody TeamRequestDto teamRequestDto) throws NotFoundException {
        return ResponseEntity.ok(teamService.updateTeam(teamId, teamRequestDto));
    }

    @DeleteMapping("/teams/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable(name = "teamId") String teamId) throws NotFoundException {
        teamService.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/teams/{teamId}/players")
    public ResponseEntity<ExtendedTeamResponseDto> getFullTeam(@PathVariable(name = "teamId") String teamId) throws NotFoundException {
        return ResponseEntity.ok(teamService.getFullTeam(teamId));
    }

    @GetMapping("/teams/{teamId}/squad")
    public ResponseEntity<FirstElevenResponseDto> getFirstEleven(@PathVariable(name = "teamId") String teamId) throws NotFoundException {
        return ResponseEntity.ok(teamService.getFirstEleven(teamId));
    }
}

