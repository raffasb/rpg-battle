package com.avanade.rpgbattle.model.dto;

import com.avanade.rpgbattle.enumeration.CharacterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleCreateRequest implements Serializable {

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private String player1Name;

    private String player2Name;

    @NotNull(message = "Player1 Character Type is mandatory")
    private CharacterType player1CharacterType;

    @NotNull(message = "Player2 Character Type is mandatory")
    private CharacterType player2CharacterType;

    @NotNull
    @Min(1)
    private Long player1CharacterId;

    @NotNull
    @Min(1)
    private Long player2CharacterId;
}
