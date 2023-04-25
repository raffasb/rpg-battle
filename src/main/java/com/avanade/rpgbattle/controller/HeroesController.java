package com.avanade.rpgbattle.controller;

import com.avanade.rpgbattle.model.Hero;
import com.avanade.rpgbattle.service.HeroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "api/heroes" )
@Api( value = "Heroes" )
@CrossOrigin( origins = "*" )
public class HeroesController {

    @Autowired
    private HeroService service;

    @GetMapping( "" )
    @ApiOperation( "Find all heroes" )
    public ResponseEntity<List<Hero>> getAll( ) {
        return new ResponseEntity<>( service.findAll( ), HttpStatus.OK );
    }

    @GetMapping( "{id}" )
    @ApiOperation( "Find a hero by its identifier" )
    public ResponseEntity< Hero > getById( @PathVariable( value = "id" ) Long heroId ) {
        return new ResponseEntity<>( service.findById( heroId ), HttpStatus.OK );
    }

    @PostMapping( "" )
    @ApiOperation( "Create a hero" )
    public ResponseEntity< Hero > create( @RequestBody Hero hero ) {
        return new ResponseEntity<>( service.create( hero ), HttpStatus.CREATED );
    }

    @PutMapping( "" )
    @ApiOperation( "Update a hero" )
    public ResponseEntity< Hero > update( @RequestBody Hero hero ) {
        return new ResponseEntity<>( service.update( hero ), HttpStatus.OK );
    }

    @DeleteMapping( "" )
    @ApiOperation( "Delete a hero" )
    public ResponseEntity< HttpStatus > delete( @RequestHeader Long heroId ) {
        service.delete( heroId );
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
}
