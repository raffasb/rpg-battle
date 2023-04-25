package com.avanade.rpgbattle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dice implements Serializable {

    @Min(1)
    private int quantityOfDices;

    @Min(1)
    private int numberOfFaces;
}

