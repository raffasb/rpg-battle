package com.avanade.rpgbattle.model;

import com.avanade.rpgbattle.enumeration.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "BATTLE_HITS" )
@Entity
public class BattleHits implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID", nullable = false )
    private Long id;

    @Min(1)
    @Column( name = "BATTLE_ID", nullable = false )
    private Long battleId;

    @Min(0)
    @Column( name = "TURN" )
    private Integer turn;

    @Column( name = "PLAYER1_CURRENT_HEALTH_POINTS", nullable = false )
    private Long player1CurrentHealthPoints;

    @Column( name = "PLAYER2_CURRENT_HEALTH_POINTS", nullable = false )
    private Long player2CurrentHealthPoints;

    @Column( name = "PLAYER1_STRENGTH_POINTS", nullable = false )
    private Long player1StrengthPoints;

    @Column( name = "PLAYER2_STRENGTH_POINTS", nullable = false )
    private Long player2StrengthPoints;

    @Column( name = "PLAYER1_DICES_VALUE" )
    private Integer player1DicesValue;

    @Column( name = "PLAYER2_DICES_VALUE" )
    private Integer player2DicesValue;

    @Enumerated(EnumType.STRING)
    @Column( name = "ATTACKER" )
    private PlayerType attacker;

    @Column( name = "DAMAGE" )
    private Integer damage;

    @Column( name = "CREATED_AT", nullable = false )
    private LocalDateTime createdAt;
}
