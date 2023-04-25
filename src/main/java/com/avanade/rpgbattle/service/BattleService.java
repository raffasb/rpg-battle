package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.exception.InvalidInputException;
import com.avanade.rpgbattle.exception.ResourceNotFoundException;
import com.avanade.rpgbattle.model.Battle;
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

    public Battle create( @Valid Battle battle ) {
        battle.setCreatedAt( LocalDateTime.now( ) );
        //battle.setIsSystemGenerated( false );

        return this.repository.save( battle );
    }

    public List<Battle> findAll( ) {
        return repository.findAll( );
    }

    public Battle findById( Long id ) {
        return repository.findById( id )
                .orElseThrow( ( ) -> new ResourceNotFoundException(
                        "Battle not found - ID: " + id ) );
    }

    public void delete( Long id ) {
        repository.deleteById( id );
    }

    public Battle update( @Valid Battle battle ) {
        if ( battle.getId( ) == null ) {
            throw new InvalidInputException( "There is no battle with the specified ID" );
        }
        return repository.save( battle );
    }

    public int calculateHealthPoints() {
        return 1;
    }
}



