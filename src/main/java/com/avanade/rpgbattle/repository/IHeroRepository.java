package com.avanade.rpgbattle.repository;

import com.avanade.rpgbattle.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHeroRepository extends JpaRepository<Hero, Long> {

}
