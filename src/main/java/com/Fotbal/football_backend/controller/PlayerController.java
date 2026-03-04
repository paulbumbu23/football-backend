package com.Fotbal.football_backend.controller;

import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.dto.player.PlayerRequestDto;
import com.Fotbal.football_backend.model.dto.player.PlayerResponseDto;
import com.Fotbal.football_backend.model.dto.team.TeamFilterRequestDto;
import com.Fotbal.football_backend.service.IPlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PlayerController {

    private final IPlayerService playerService;

    @GetMapping("/players")
    public ResponseEntity<List<PlayerResponseDto>> getPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/players/{playerId}")
    public ResponseEntity<PlayerResponseDto> getPlayerById(@PathVariable("playerId") String playerId) throws NotFoundException {
        return ResponseEntity.ok(playerService.getPlayerById(playerId));
    }

    @PostMapping("/players")
    public ResponseEntity<PlayerResponseDto> addPlayer(@RequestBody PlayerRequestDto playerRequestDto) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.addPlayer(playerRequestDto));
    }

    @PutMapping("/players/{playerId}")
    public ResponseEntity<PlayerResponseDto> updatePlayer(@PathVariable("playerId") String playerId, @RequestBody PlayerRequestDto playerRequestDto) throws NotFoundException {
        return ResponseEntity.ok(playerService.updatePlayerById(playerId, playerRequestDto));
    }

    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("playerId") String playerId) throws NotFoundException {
        playerService.deletePlayerById(playerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("players/teamName")
    public ResponseEntity<List<PlayerResponseDto>> getPlayersByTeamName(@RequestBody TeamFilterRequestDto teamFilterRequestDto) throws NotFoundException {
        return ResponseEntity.ok(playerService.getPlayersByTeamName(teamFilterRequestDto));
    }
}

