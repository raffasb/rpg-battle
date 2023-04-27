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
public class BattleAttackRequest implements Serializable {

    @NotNull
    @Min(1)
    private Integer dicesValue;

    @NotNull
    private PlayerType attacker;
}
