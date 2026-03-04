package com.Fotbal.football_backend.model.entity;

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
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String championshipId;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    private String country;

    @OneToMany(mappedBy = "championship", cascade = CascadeType.ALL)
    private List<Team> teams;
}
