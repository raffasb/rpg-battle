package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.exception.InvalidInputException;
import com.avanade.rpgbattle.exception.ResourceNotFoundException;
import com.avanade.rpgbattle.model.Monster;
import com.avanade.rpgbattle.repository.IMonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
public class MonsterService {
    @Autowired
    private IMonsterRepository repository;

    public Monster create( @Valid Monster monster ) {
        monster.setCreatedAt( LocalDateTime.now( ) );
        monster.setIsSystemGenerated( false );

        return this.repository.save( monster );
    }

    public List<Monster> findAll( ) {
        return repository.findAll( );
    }

    public Monster findById( Long id ) {
        return repository.findById( id )
                .orElseThrow( ( ) -> new ResourceNotFoundException(
                        "Monster not found - ID: " + id ) );
    }

    public void delete( Long id ) {
        repository.deleteById( id );
    }

    public Monster update( @Valid Monster monster ) {
        if ( monster.getId( ) == null ) {
            throw new InvalidInputException( "There is no monster with the specified ID" );
        }
        return repository.save( monster );
    }
}