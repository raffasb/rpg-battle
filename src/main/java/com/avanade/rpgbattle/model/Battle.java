package com.avanade.rpgbattle.model;

import com.avanade.rpgbattle.enumeration.CharacterType;
import com.avanade.rpgbattle.enumeration.PlayerType;
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

    @Column( name = "PLAYER1_NAME" )
    private String player1Name;

    @Column( name = "PLAYER2_NAME" )
    private String player2Name;

    @Enumerated(EnumType.STRING)
    @Column( name = "PLAYER1_CHARACTER_TYPE", nullable = false )
    private CharacterType player1CharacterType;

    @Enumerated(EnumType.STRING)
    @Column( name = "PLAYER2_CHARACTER_TYPE", nullable = false )
    private CharacterType player2CharacterType;

    @Column( name = "PLAYER1_CHARACTER_ID", nullable = false )
    private Long player1CharacterId;

    @Column( name = "PLAYER2_CHARACTER_ID", nullable = false )
    private Long player2CharacterId;

    @Enumerated(EnumType.STRING)
    @Column( name = "INITIATIVE" )
    private PlayerType initiative;

    @Column( name = "CREATED_AT", nullable = false )
    private LocalDateTime createdAt;

    @Column( name = "COMPLETED_AT" )
    private LocalDateTime completedAt;

    @Column( name = "IS_FINISHED", nullable = false )
    private Boolean isFinished;

    @Enumerated(EnumType.STRING)
    @Column( name = "WINNER" )
    private PlayerType winner;
}
