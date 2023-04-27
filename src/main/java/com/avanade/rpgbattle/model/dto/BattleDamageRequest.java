package com.avanade.rpgbattle.model.dto;

import com.avanade.rpgbattle.enumeration.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleDamageRequest implements Serializable {

    @NotNull
    private PlayerType attacker;

    @NotNull
    @Min(1)
    private Integer attackerDicesValue;

    @NotNull
    @Min(1)
    private Integer totalAttackValue;

    @NotNull
    @Min(1)
    private Integer defenderDicesValue;

    @NotNull
    @Min(1)
    private Integer totalDefenseValue;
}
