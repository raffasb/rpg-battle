package com.avanade.rpgbattle.model;

import com.avanade.rpgbattle.enumeration.CharacterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "BATTLE_LOGS" )
@Entity
public class BattleLog implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID", nullable = false )
    private Long id;

    @Column( name = "BATTLE_ID", nullable = false )
    private Long battleId;

    @Column( name = "TURN", nullable = false )
    private Long turn;

    @Column( name = "PLAYER1_CURRENT_HEALTH_POINTS", nullable = false )
    private Long player1CurrentHealthPoints;

    @Column( name = "PLAYER2_CURRENT_HEALTH_POINTS", nullable = false )
    private Long player2CurrentHealthPoints;

    @Column( name = "PLAYER1_PREVIOUS_HEALTH_POINTS" )
    private Long player1PreviousHealthPoints;

    @Column( name = "PLAYER2_PREVIOUS_HEALTH_POINTS" )
    private Long player2PreviousHealthPoints;
}
