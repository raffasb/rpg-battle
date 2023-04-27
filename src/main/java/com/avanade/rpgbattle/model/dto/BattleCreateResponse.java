package com.avanade.rpgbattle.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleCreateResponse implements Serializable {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private Boolean isFinished;
}
