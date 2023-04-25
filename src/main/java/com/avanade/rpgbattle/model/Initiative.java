package com.avanade.rpgbattle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Initiative implements Serializable {

    @Min(1)
    private Long id;

    @Min(1)
    private Long battleId;
}
