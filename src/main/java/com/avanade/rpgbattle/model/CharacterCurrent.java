package com.avanade.rpgbattle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterCurrent implements Serializable {
    private int healthPoints;
    private int strengthPoints;
    private int defensePoints;
    private int agilityPoints;
}
