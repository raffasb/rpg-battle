package com.avanade.rpgbattle.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleDamageResponse implements Serializable {
    private Integer totalDamageValue;
}
