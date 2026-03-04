package com.Fotbal.football_backend.repository;

import com.Fotbal.football_backend.model.entity.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, String> {
    Optional<Championship> findChampionshipByName(String name);
}
