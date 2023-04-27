package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.enumeration.CharacterType;
import com.avanade.rpgbattle.enumeration.PlayerType;
import com.avanade.rpgbattle.model.Battle;
import com.avanade.rpgbattle.model.BattleHits;
import com.avanade.rpgbattle.model.Hero;
import com.avanade.rpgbattle.model.dto.BattleCreateRequest;
import com.avanade.rpgbattle.model.dto.BattleCreateResponse;
import com.avanade.rpgbattle.repository.IBattleHitsRepository;
import com.avanade.rpgbattle.repository.IBattleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BattleServiceTest {

    @InjectMocks
    private BattleService service;
    @Mock
    private DiceService diceService;
    @Mock
    private HeroService heroService;
    @Mock
    private MonsterService monsterService;
    @Mock
    private IBattleRepository repository;
    @Mock
    private IBattleHitsRepository hitsRepository;

    List<Battle> itemsList;

    List<BattleHits> hitsList;

    @BeforeEach
    void setUp( ) {
        MockitoAnnotations.openMocks( this );

        itemsList = new ArrayList<>( );
        itemsList.add( new Battle(1L, "Item 1", "Unit Test 1", "P1", "P2", CharacterType.Hero, CharacterType.Monster, 1L, 1L, PlayerType.Player1, LocalDateTime.now( ), LocalDateTime.now( ).plusMinutes(6L), true, PlayerType.Player1) );
        itemsList.add( new Battle(1L, "Item 2", "Unit Test 2", "P1", "P2", CharacterType.Monster, CharacterType.Monster, 2L, 3L, PlayerType.Player2, LocalDateTime.now( ).plusHours(2L), null, false, null) );

        hitsList = new ArrayList<>( );
        hitsList.add( new BattleHits(1L, 1L, 0, 100, 200, 5, 10, null, null, null, null, null, null, LocalDateTime.now( )) );
        hitsList.add( new BattleHits(2L, 1L, 1, 100, 170, 5, 10, 16, 9, 25, 17, PlayerType.Player1, 30, LocalDateTime.now( ).plusMinutes(1L)) );
        hitsList.add( new BattleHits(3L, 1L, 2, 80, 170, 5, 10, 11, 10, 8, 10, PlayerType.Player2, 20, LocalDateTime.now( ).plusMinutes(1L)) );
        hitsList.add( new BattleHits(4L, 1L, 3, 80, 60, 5, 10, 18, 3, 105, 68, PlayerType.Player1, 110, LocalDateTime.now( ).plusMinutes(1L)) );
        hitsList.add( new BattleHits(5L, 1L, 4, 30, 60, 5, 10, 14, 13, 28, 40, PlayerType.Player2, 50, LocalDateTime.now( ).plusMinutes(1L)) );
        hitsList.add( new BattleHits(6L, 1L, 5, 30, 60, 5, 10, 16, 17, 25, 27, PlayerType.Player1, null, LocalDateTime.now( ).plusMinutes(1L)) );
        hitsList.add( new BattleHits(7L, 1L, 6, -10, 60, 5, 10, 8, 5, 14, 30, PlayerType.Player2, 40, LocalDateTime.now( ).plusMinutes(1L)) );
        hitsList.add( new BattleHits(8L, 2L, 0, 100, 200, 5, 10, null, null, null, null, null, null, LocalDateTime.now( ).plusHours(2L)) );
    }

    @Test
    void create() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void findAllBattleHitsLogs() {
    }

    @Test
    void initiative() {
    }

    @Test
    void damage() {
    }

    @Test
    void attack() {
    }

    @Test
    void defense() {
    }
}