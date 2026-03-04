package com.Fotbal.football_backend.controller;

import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.dto.championship.ChampionshipRequestDto;
import com.Fotbal.football_backend.model.dto.championship.ChampionshipResponseDto;
import com.Fotbal.football_backend.service.IChampionshipService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ChampionshipController {

    private final IChampionshipService championshipService;

    @GetMapping("/championships")
    public ResponseEntity<List<ChampionshipResponseDto>> getChampionships() {
        return ResponseEntity.ok(championshipService.getChampionships());
    }

    @GetMapping("/championships/{championshipId}")
    public ResponseEntity<ChampionshipResponseDto> getChampionshipById(@PathVariable String championshipId) throws NotFoundException {
        return ResponseEntity.ok(championshipService.getChampionshipById(championshipId));
    }

    @PostMapping("/championships")
    public ResponseEntity<ChampionshipResponseDto> addChampionship(@RequestBody ChampionshipRequestDto championshipRequestDto) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(championshipService.addChampionship(championshipRequestDto));
    }

    @PutMapping("championships/{championshipId}")
    public ResponseEntity<ChampionshipResponseDto> updateChampionship(@PathVariable("championshipId") String championshipId, @RequestBody ChampionshipRequestDto championshipRequestDto) throws NotFoundException {
        return ResponseEntity.ok(championshipService.updateChampionship(championshipId, championshipRequestDto));
    }

    @DeleteMapping("/championships/{championshipId}")
    public ResponseEntity<Void> deleteChampionship(@PathVariable String championshipId) throws NotFoundException {
        championshipService.deleteChampionshipById(championshipId);
        return ResponseEntity.noContent().build();
    }
}
