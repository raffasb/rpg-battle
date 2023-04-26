package com.avanade.rpgbattle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character implements Serializable {
    private Long id;
    private String name;
    private String description;
    private int healthPoints;
    private int strengthPoints;
    private int defensePoints;
    private int agilityPoints;
    private int diceQuantity;
    private int diceFaces;
    private LocalDateTime createdAt;
    private Boolean isSystemGenerated;

    private int currentHealthPoints;
}
