package com.avanade.rpgbattle.model.dto;

import com.avanade.rpgbattle.enumeration.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleAttackRequest implements Serializable {

    @Min(1)
    private int dicesValue;

    private PlayerType attacker;
}
