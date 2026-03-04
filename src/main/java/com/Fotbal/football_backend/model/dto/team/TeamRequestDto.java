package com.Fotbal.football_backend.model.dto.team;

import com.Fotbal.football_backend.model.enums.Formation;
import lombok.Data;

@Data
public class TeamRequestDto {
    private String teamName;
    private String championshipName;
    private Formation formation;
}
