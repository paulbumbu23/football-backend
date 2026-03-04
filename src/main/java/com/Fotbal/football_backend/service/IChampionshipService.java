package com.Fotbal.football_backend.service;

import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.dto.championship.ChampionshipRequestDto;
import com.Fotbal.football_backend.model.dto.championship.ChampionshipResponseDto;

import java.util.List;

public interface IChampionshipService {
    List<ChampionshipResponseDto> getChampionships();

    ChampionshipResponseDto getChampionshipById(String championshipId) throws NotFoundException;

    ChampionshipResponseDto addChampionship(ChampionshipRequestDto championshipRequestDto) throws NotFoundException;

    ChampionshipResponseDto updateChampionship(String championshipId, ChampionshipRequestDto championshipRequestDto) throws NotFoundException;

    void deleteChampionshipById(String championshipId) throws NotFoundException;
}
