package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.exception.InvalidInputException;
import com.avanade.rpgbattle.exception.ResourceNotFoundException;
import com.avanade.rpgbattle.model.Hero;
import com.avanade.rpgbattle.repository.IHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HeroService {
    @Autowired
    private IHeroRepository repository;

    public Hero create( Hero hero ) {
        hero.setCreatedAt( LocalDateTime.now( ) );
        hero.setIsSystemGenerated( false );

        return this.repository.save( hero );
    }

    public List<Hero> findAll( ) {
        return repository.findAll( );
    }

    public Hero findById( Long id ) {
        return repository.findById( id )
                .orElseThrow( ( ) -> new ResourceNotFoundException(
                        "Hero not found - ID: " + id ) );
    }

    public void delete( Long id ) {
        repository.deleteById( id );
    }

    public Hero update(Hero hero ) {
        if ( hero.getId( ) == null ) {
            throw new InvalidInputException( "There is no hero with the specified ID" );
        }
        return repository.save( hero );
    }
}


