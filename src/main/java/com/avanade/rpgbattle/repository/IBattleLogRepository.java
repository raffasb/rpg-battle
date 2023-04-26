package com.avanade.rpgbattle.repository;

import com.avanade.rpgbattle.model.BattleLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBattleLogRepository extends JpaRepository<BattleLog, Long> {
    List<BattleLog> findByBattleId(Long battleId);
}