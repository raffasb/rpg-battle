package com.avanade.rpgbattle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player implements Serializable {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Character Type is mandatory")
    private String characterType;
}
