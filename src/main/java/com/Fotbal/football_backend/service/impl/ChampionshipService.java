package com.Fotbal.football_backend.service.impl;

import com.Fotbal.football_backend.exception.NotFoundException;
import com.Fotbal.football_backend.model.converter.ChampionshipConverter;
import com.Fotbal.football_backend.model.dto.championship.ChampionshipRequestDto;
import com.Fotbal.football_backend.model.dto.championship.ChampionshipResponseDto;
import com.Fotbal.football_backend.model.entity.Championship;
import com.Fotbal.football_backend.repository.ChampionshipRepository;
import com.Fotbal.football_backend.service.IChampionshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChampionshipService implements IChampionshipService {
    private final ChampionshipRepository championshipRepository;
    private final ChampionshipConverter championshipConverter;

    public List<ChampionshipResponseDto> getChampionships() {
        return championshipRepository.findAll()
                .stream()
                .map(championshipConverter::convertChampionshipToResponseDto)
                .collect(Collectors.toList());
    }

    public ChampionshipResponseDto getChampionshipById(String id) throws NotFoundException {
        Optional<Championship> championship = championshipRepository.findById(id);
        if (championship.isEmpty()) {
            throw new NotFoundException(String.format("Championship with id %s not found", id));
        }
        return championshipConverter.convertChampionshipToResponseDto(championship.get());
    }

    public ChampionshipResponseDto addChampionship(ChampionshipRequestDto championshipRequestDto) throws NotFoundException {
        Championship championship = championshipConverter.convertRequestToChampionship(championshipRequestDto);
        Championship savedChampionship = championshipRepository.save(championship);
        return championshipConverter.convertChampionshipToResponseDto(savedChampionship);
    }

    public ChampionshipResponseDto updateChampionship(String championshipId, ChampionshipRequestDto championshipRequestDto) throws NotFoundException {
        Optional<Championship> optionalChampionship = championshipRepository.findById(championshipId);
        if (optionalChampionship.isEmpty()) {
            throw new NotFoundException(String.format("Championship with id %s not found", championshipId));
        }
        Championship championship = optionalChampionship.get();
        championship.setName(championshipRequestDto.getChampionshipName());
        championship.setCountry(championshipRequestDto.getChampionshipCountry());
        Championship updatedChampionship = championshipRepository.save(championship);
        return championshipConverter.convertChampionshipToResponseDto(updatedChampionship);
    }

    public void deleteChampionshipById(String championshipId) throws NotFoundException {
        Optional<Championship> championship = championshipRepository.findById(championshipId);
        if (championship.isEmpty()) {
            throw new NotFoundException(String.format("Championship with id %s not found", championshipId));
        }
        championshipRepository.delete(championship.get());
    }
}
