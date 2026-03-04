package com.Fotbal.football_backend.model.entity;

import com.Fotbal.football_backend.model.enums.Position;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String playerId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private int numberOnShirt;
    @NotNull
    private Position position;
    @NotNull
    private int overall;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "imageId")
    private Image profileImage;
}
