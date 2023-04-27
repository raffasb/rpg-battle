package com.avanade.rpgbattle.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiceThrowRequest implements Serializable {

    @NotNull
    @Min(1)
    private int quantityOfDices;

    @NotNull
    @Min(1)
    private int numberOfFaces;
}
