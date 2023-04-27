package com.avanade.rpgbattle.repository;

import com.avanade.rpgbattle.model.BattleHits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBattleHitsRepository extends JpaRepository<BattleHits, Long> {
    List<BattleHits> findByBattleIdOrderByTurnAsc(Long battleId);
    BattleHits findFirstByBattleIdOrderByTurnDesc(Long battleId);
}