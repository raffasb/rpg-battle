package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.enumeration.CharacterType;
import com.avanade.rpgbattle.enumeration.PlayerType;
import com.avanade.rpgbattle.exception.InvalidInputException;
import com.avanade.rpgbattle.exception.ResourceNotFoundException;
import com.avanade.rpgbattle.model.*;
import com.avanade.rpgbattle.repository.IBattleLogRepository;
import com.avanade.rpgbattle.repository.IBattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
public class BattleService {
    @Autowired
    private IBattleRepository repository;
    @Autowired
    private IBattleLogRepository logsRepository;
    @Autowired
    private DiceService diceService;
    @Autowired
    private HeroService heroService;
    @Autowired
    private MonsterService monsterService;

    public Battle create( @Valid Battle battle )
    {
        battle.setCreatedAt( LocalDateTime.now( ) );
        battle.setCompletedAt( null );
        battle.setIsFinished( false );
        battle.setWinner( null );
        battle.setInitiative( null );

        return this.repository.save( battle );
    }

    public List<Battle> findAll( ) {
        return repository.findAll( );
    }

    public Battle findById( Long id )
    {
        return repository.findById( id )
                .orElseThrow( ( ) -> new ResourceNotFoundException(
                        "Battle not found - ID: " + id ) );
    }

    public void delete( Long id ) {
        repository.deleteById( id );
    }

    public Battle update( @Valid Battle battle )
    {
        if ( battle.getId( ) == null ) {
            throw new InvalidInputException( "There is no battle with the specified ID" );
        }
        return repository.save( battle );
    }

    public List<BattleLog> findAllLogsByBattleId( Long battleId ) {
        return logsRepository.findByBattleId( battleId );
    }

    public Initiative initiative( Long battleId )
    {
        int numberOfDices = 1;
        int numberOfFaces = 20;

        int player1DiceValue = 1;
        int player2DiceValue = 1;

        Dice dice = new Dice(numberOfDices, numberOfFaces, -1);

        do
        {
            player1DiceValue = diceService.throwDices(dice);
            player2DiceValue = diceService.throwDices(dice);

            //TODO: Log values to database...

        } while (player1DiceValue != player2DiceValue);

        PlayerType whoStarts = player1DiceValue > player2DiceValue ? PlayerType.Player1 : PlayerType.Player2;

        Battle battle = findById( battleId );
        battle.setInitiative(whoStarts);
        repository.save(battle);

        Initiative initiative = new Initiative(battleId, player1DiceValue, player2DiceValue, whoStarts);

        return initiative;
    }

    public BattleStatus status( Long battleId )
    {
        return new BattleStatus();
    }

    public int calculateHealthPoints() {
        return 1;
    }

    public int attack( Long battleId, int diceValue, PlayerType attacker )
    {
        //int numberOfDices = 1;
        //int numberOfFaces = 12;

        //Dice dice = new Dice(numberOfDices, numberOfFaces, -1);

        //int diceValue = diceService.throwDices(dice);

        int strengthPoints = 0;
        int agilityPoints = 0;

        long characterId = 0;
        CharacterType characterType = CharacterType.Hero;

        Battle battle = findById( battleId );

        if (attacker == PlayerType.Player1) {
            characterId = battle.getPlayer1CharacterId();
            characterType = battle.getPlayer1CharacterType();
        }
        else if (attacker == PlayerType.Player2) {
            characterId = battle.getPlayer2CharacterId();
            characterType = battle.getPlayer2CharacterType();
        }

        if (characterType == CharacterType.Hero) {
            Hero hero = heroService.findById( characterId );

            strengthPoints = hero.getStrengthPoints();
            agilityPoints = hero.getAgilityPoints();
        }
        else if (characterType == CharacterType.Monster) {
            Monster monster = monsterService.findById( characterId );

            strengthPoints = monster.getStrengthPoints();
            agilityPoints = monster.getAgilityPoints();
        }

        //TODO: Save data in database...

        int totalAttackPoints = diceValue + strengthPoints + agilityPoints;

        return totalAttackPoints;
    }

    public int defense( Long battleId, int diceValue, PlayerType defender )
    {
//        int numberOfDices = 1;
//        int numberOfFaces = 12;
//
//        Dice dice = new Dice(numberOfDices, numberOfFaces, -1);
//
//        int diceValue = diceService.throwDices(dice);
//
//        int defensePoints = 0;
//        int agilityPoints = 0;
//
//        int result = diceValue + defensePoints + agilityPoints;
//
//
//        return result;

        int defensePoints = 0;
        int agilityPoints = 0;

        long characterId = 0;
        CharacterType characterType = CharacterType.Hero;

        Battle battle = findById( battleId );

        if (defender == PlayerType.Player1) {
            characterId = battle.getPlayer1CharacterId();
            characterType = battle.getPlayer1CharacterType();
        }
        else if (defender == PlayerType.Player2) {
            characterId = battle.getPlayer2CharacterId();
            characterType = battle.getPlayer2CharacterType();
        }

        if (characterType == CharacterType.Hero) {
            Hero hero = heroService.findById( characterId );

            defensePoints = hero.getDefensePoints();
            agilityPoints = hero.getAgilityPoints();
        }
        else if (characterType == CharacterType.Monster) {
            Monster monster = monsterService.findById( characterId );

            defensePoints = monster.getDefensePoints();
            agilityPoints = monster.getAgilityPoints();
        }

        //TODO: Save data in database...

        int totalDefensePoints = diceValue + defensePoints + agilityPoints;

        return totalDefensePoints;
    }
}