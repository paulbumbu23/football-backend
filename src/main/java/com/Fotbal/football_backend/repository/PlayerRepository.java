package com.Fotbal.football_backend.repository;

import com.Fotbal.football_backend.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> getPlayersByTeam_Name(String teamName);

    List<Player> getPlayersByTeam_TeamId(String teamId);
}
