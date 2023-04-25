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
@Table( name = "BATTLES" )
@Entity
public class Battle implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID", nullable = false )
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column( name = "NAME", nullable = false )
    private String name;

    @Column( name = "DESCRIPTION" )
    private String description;

//    @Column( name = "HEALTH", nullable = false )
//    private int health;
//
//    @Column( name = "STRENGTH", nullable = false )
//    private int strength;
//
//    @Column( name = "DEFENSE", nullable = false )
//    private int defense;
//
//    @Column( name = "AGILITY", nullable = false )
//    private int agility;
//
//    @Column( name = "DICE_QUANTITY", nullable = false )
//    private int diceQuantity;
//
//    @Column( name = "DICE_FACES", nullable = false )
//    private int diceFaces;

    @Column( name = "CREATED_AT", nullable = false )
    private LocalDateTime createdAt;

//    @Column( name = "IS_SYSTEM_GENERATED", nullable = false )
//    private Boolean isSystemGenerated;
}
