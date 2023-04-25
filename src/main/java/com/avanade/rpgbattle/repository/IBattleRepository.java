package com.avanade.rpgbattle.repository;

import com.avanade.rpgbattle.model.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBattleRepository extends JpaRepository<Battle, Long> {

}
