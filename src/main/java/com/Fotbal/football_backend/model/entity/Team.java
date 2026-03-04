package com.Fotbal.football_backend.model.entity;

import com.Fotbal.football_backend.model.enums.Formation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String teamId;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    private Formation formation;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "imageId")
    private Image logoImage;
}
