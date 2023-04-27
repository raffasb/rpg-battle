package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.enumeration.CharacterType;
import com.avanade.rpgbattle.enumeration.PlayerType;
import com.avanade.rpgbattle.exception.InvalidInputException;
import com.avanade.rpgbattle.exception.ResourceNotFoundException;
import com.avanade.rpgbattle.model.*;
import com.avanade.rpgbattle.model.dto.*;
import com.avanade.rpgbattle.repository.IBattleHitsRepository;
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
    private IBattleHitsRepository hitsRepository;
    @Autowired
    private DiceService diceService;
    @Autowired
    private HeroService heroService;
    @Autowired
    private MonsterService monsterService;

    public BattleCreateResponse create( @Valid BattleCreateRequest request )
    {
        Battle battle = new Battle();

        battle.setName( request.getName() );
        battle.setDescription( request.getDescription() );
        battle.setPlayer1Name( request.getPlayer1Name() );
        battle.setPlayer2Name( request.getPlayer2Name() );
        battle.setPlayer1CharacterType( request.getPlayer1CharacterType() );
        battle.setPlayer2CharacterType( request.getPlayer2CharacterType() );
        battle.setPlayer1CharacterId( request.getPlayer1CharacterId() );
        battle.setPlayer2CharacterId( request.getPlayer2CharacterId() );

        battle.setCreatedAt( LocalDateTime.now( ) );
        battle.setCompletedAt( null );
        battle.setIsFinished( false );
        battle.setWinner( null );
        battle.setInitiative( null );

        Battle result = repository.save( battle );

        createDefaultBattleHit( result );

        return new BattleCreateResponse(result.getId(), result.getName(), result.getCreatedAt(), result.getIsFinished());
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

    public List<BattleHits> findAllBattleHitsLogs(Long battleId ) {
        return hitsRepository.findByBattleIdOrderByTurnAsc( battleId );
    }

    public Initiative initiative( Long battleId )
    {
        Integer numberOfDices = 1;
        Integer numberOfFaces = 20;

        Integer player1DiceValue = 1;
        Integer player2DiceValue = 1;

        Dice dice = new Dice(numberOfDices, numberOfFaces);

        do
        {
            player1DiceValue = diceService.throwDices(dice).getDicesTotalValue();
            player2DiceValue = diceService.throwDices(dice).getDicesTotalValue();

        } while ( player1DiceValue.equals(player2DiceValue) );

        PlayerType whoStarts = player1DiceValue > player2DiceValue ? PlayerType.Player1 : PlayerType.Player2;

        Battle battle = findById( battleId );
        battle.setInitiative( whoStarts );
        repository.save(battle);

        return new Initiative(battleId, player1DiceValue, player2DiceValue, whoStarts);
    }

    public BattleDamageResponse damage(Long battleId, BattleDamageRequest request) {

        LocalDateTime localDateTime = LocalDateTime.now( );

        Battle battle = findById( battleId );

        BattleHits latestBattleHits = hitsRepository.findFirstByBattleIdOrderByTurnDesc( battleId );

        Integer player1CurrentHealthPoints = latestBattleHits.getPlayer1CurrentHealthPoints();
        Integer player2CurrentHealthPoints = latestBattleHits.getPlayer2CurrentHealthPoints();

        Integer player1DicesValue = 0;
        Integer player2DicesValue = 0;

        Long attackerCharacterId = 0L;
        CharacterType attackerCharacterType = CharacterType.Hero;

        if (request.getAttacker() == PlayerType.Player1) {
            attackerCharacterId = battle.getPlayer1CharacterId();
            attackerCharacterType = battle.getPlayer1CharacterType();

            player1DicesValue = request.getAttackerDicesValue();
            player2DicesValue = request.getDefenderDicesValue();
        }
        else if (request.getAttacker() == PlayerType.Player2) {
            attackerCharacterId = battle.getPlayer2CharacterId();
            attackerCharacterType = battle.getPlayer2CharacterType();

            player1DicesValue = request.getDefenderDicesValue();
            player2DicesValue = request.getAttackerDicesValue();
        }

        Integer totalDamagePoints = null;

        if ( request.getTotalAttackValue() > request.getTotalDefenseValue() )
        {
            //region Calculate the damage caused by the undefended attack
            Integer strengthPoints = 0;

            if (attackerCharacterType == CharacterType.Hero) {
                Hero hero = heroService.findById( attackerCharacterId );
                strengthPoints = hero.getStrengthPoints();
            }
            else if (attackerCharacterType == CharacterType.Monster) {
                Monster monster = monsterService.findById( attackerCharacterId );
                strengthPoints = monster.getStrengthPoints();
            }

            totalDamagePoints = request.getAttackerDicesValue() + strengthPoints;

            if (request.getAttacker() == PlayerType.Player1) {
                player2CurrentHealthPoints = latestBattleHits.getPlayer2CurrentHealthPoints() - totalDamagePoints;
            }
            else if (request.getAttacker() == PlayerType.Player2) {
                player1CurrentHealthPoints = latestBattleHits.getPlayer1CurrentHealthPoints() - totalDamagePoints;
            }
            //endregion
        }

        //region Create entry in the BATTLE_HITS table
        BattleHits battleHits = new BattleHits();

        battleHits.setBattleId( battle.getId() );
        battleHits.setTurn( latestBattleHits.getTurn() + 1 );
        battleHits.setPlayer1CurrentHealthPoints( player1CurrentHealthPoints ) ;
        battleHits.setPlayer2CurrentHealthPoints( player2CurrentHealthPoints ) ;
        battleHits.setPlayer1StrengthPoints( latestBattleHits.getPlayer1StrengthPoints() ) ;
        battleHits.setPlayer2StrengthPoints( latestBattleHits.getPlayer2StrengthPoints() ) ;
        battleHits.setTotalAttackValue( request.getTotalAttackValue() );
        battleHits.setTotalDefenseValue( request.getTotalDefenseValue() );
        battleHits.setPlayer1DicesValue( player1DicesValue );
        battleHits.setPlayer2DicesValue( player2DicesValue ) ;
        battleHits.setAttacker( request.getAttacker() ) ;
        battleHits.setDamage( totalDamagePoints ) ;
        battleHits.setCreatedAt( localDateTime ) ;

        hitsRepository.save( battleHits );
        //endregion

        //region Check if the battle could be finished
        Boolean isFinished = false;

        if (request.getAttacker() == PlayerType.Player1) {
            isFinished = player2CurrentHealthPoints <= 0;
        }
        else if (request.getAttacker() == PlayerType.Player2) {
            isFinished = player1CurrentHealthPoints <= 0;
        }
        //endregion

        if ( isFinished )
        {
            //region Update entry in the BATTLES table
            battle.setIsFinished( true );
            battle.setWinner( request.getAttacker() );
            battle.setCompletedAt( localDateTime );

            repository.save( battle );
            //endregion
        }

        return new BattleDamageResponse(totalDamagePoints);
    }

    public BattleAttackResponse attack(Long battleId, BattleAttackRequest request)
    {
        Integer strengthPoints = 0;
        Integer agilityPoints = 0;

        Long characterId = 0L;
        CharacterType characterType = CharacterType.Hero;

        Battle battle = findById( battleId );

        if (request.getAttacker() == PlayerType.Player1) {
            characterId = battle.getPlayer1CharacterId();
            characterType = battle.getPlayer1CharacterType();
        }
        else if (request.getAttacker() == PlayerType.Player2) {
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

        Integer totalAttackPoints = request.getDicesValue() + strengthPoints + agilityPoints;

        return new BattleAttackResponse(totalAttackPoints);
    }

    public BattleDefenseResponse defense(Long battleId, BattleDefenseRequest request )
    {
        Integer defensePoints = 0;
        Integer agilityPoints = 0;

        Long characterId = 0L;
        CharacterType characterType = CharacterType.Hero;

        Battle battle = findById( battleId );

        if (request.getDefender() == PlayerType.Player1) {
            characterId = battle.getPlayer1CharacterId();
            characterType = battle.getPlayer1CharacterType();
        }
        else if (request.getDefender() == PlayerType.Player2) {
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

        Integer totalDefensePoints = request.getDicesValue() + defensePoints + agilityPoints;

        return new BattleDefenseResponse(totalDefensePoints);
    }

    private BattleHits createDefaultBattleHit( @Valid Battle battle ) {

        Integer player1HealthPoints = 0;
        Integer player1StrengthPoints = 0;

        if (battle.getPlayer1CharacterType() == CharacterType.Hero) {
            Hero hero = heroService.findById( battle.getPlayer1CharacterId() );

            player1HealthPoints = hero.getHealthPoints();
            player1StrengthPoints = hero.getStrengthPoints();
        }
        else if (battle.getPlayer1CharacterType() == CharacterType.Monster) {
            Monster monster = monsterService.findById( battle.getPlayer1CharacterId() );

            player1HealthPoints = monster.getHealthPoints();
            player1StrengthPoints = monster.getStrengthPoints();
        }

        Integer player2HealthPoints = 0;
        Integer player2StrengthPoints = 0;

        if (battle.getPlayer2CharacterType() == CharacterType.Hero) {
            Hero hero = heroService.findById( battle.getPlayer2CharacterId() );

            player2HealthPoints = hero.getHealthPoints();
            player2StrengthPoints = hero.getStrengthPoints();
        }
        else if (battle.getPlayer2CharacterType() == CharacterType.Monster) {
            Monster monster = monsterService.findById( battle.getPlayer2CharacterId() );

            player2HealthPoints = monster.getHealthPoints();
            player2StrengthPoints = monster.getStrengthPoints();
        }

        BattleHits battleHits = new BattleHits();

        battleHits.setBattleId( battle.getId() );
        battleHits.setTurn( 0 );
        battleHits.setPlayer1CurrentHealthPoints( player1HealthPoints ) ;
        battleHits.setPlayer2CurrentHealthPoints( player2HealthPoints ) ;
        battleHits.setPlayer1StrengthPoints( player1StrengthPoints ) ;
        battleHits.setPlayer2StrengthPoints( player2StrengthPoints ) ;
        battleHits.setTotalAttackValue( null );
        battleHits.setTotalDefenseValue( null );
        battleHits.setPlayer1DicesValue( null );
        battleHits.setPlayer2DicesValue( null ) ;
        battleHits.setAttacker( null ) ;
        battleHits.setDamage( null ) ;
        battleHits.setCreatedAt( battle.getCreatedAt() ) ;

        return hitsRepository.save( battleHits );
    }
}