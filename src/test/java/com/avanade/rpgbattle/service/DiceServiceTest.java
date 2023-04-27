package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.model.Dice;
import com.avanade.rpgbattle.model.Hero;
import com.avanade.rpgbattle.model.dto.DiceThrowResponse;
import com.avanade.rpgbattle.repository.IHeroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DiceServiceTest {

    @InjectMocks
    private DiceService service;

    @BeforeEach
    void setUp( ) {
        MockitoAnnotations.openMocks( this );
    }
    @Test
    void throwDices() {
        Dice dice = new Dice(2, 6);
        DiceThrowResponse response = service.throwDices( dice );
        Assertions.assertTrue(response.getDicesTotalValue() >= 2);
    }
}