package com.Fotbal.football_backend.model.dto.player;

import com.Fotbal.football_backend.model.enums.Position;
import lombok.Data;

@Data
public class PlayerRequestDto {
    private String firstName;
    private String lastName;
    private String teamName;
    private int numberOnShirt;
    private Position position;
    private int overall;
}
