package com.avanade.rpgbattle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "MONSTERS" )
@Entity
public class Monster implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID", nullable = false )
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column( name = "NAME", nullable = false )
    private String name;

    @Column( name = "DESCRIPTION" )
    private String description;

    @Column( name = "HEALTH_POINTS", nullable = false )
    private int healthPoints;

    @Column( name = "STRENGTH_POINTS", nullable = false )
    private int strengthPoints;

    @Column( name = "DEFENSE_POINTS", nullable = false )
    private int defensePoints;

    @Column( name = "AGILITY_POINTS", nullable = false )
    private int agilityPoints;

    @Column( name = "DICE_QUANTITY", nullable = false )
    private int diceQuantity;

    @Column( name = "DICE_FACES", nullable = false )
    private int diceFaces;

    @Column( name = "CREATED_AT", nullable = false )
    private LocalDateTime createdAt;

    @Column( name = "IS_SYSTEM_GENERATED", nullable = false )
    private Boolean isSystemGenerated;
}

