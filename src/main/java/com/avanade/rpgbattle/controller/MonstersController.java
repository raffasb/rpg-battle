package com.avanade.rpgbattle.controller;

import com.avanade.rpgbattle.model.Monster;
import com.avanade.rpgbattle.service.MonsterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "api/monsters" )
@Api( value = "Monster" )
@CrossOrigin( origins = "*" )
public class MonstersController {

    @Autowired
    private MonsterService service;

    @GetMapping( "" )
    @ApiOperation( "Find all monsters" )
    public ResponseEntity<List< Monster >> getAll( ) {
        return new ResponseEntity<>( service.findAll( ), HttpStatus.OK );
    }

    @GetMapping( "{id}" )
    @ApiOperation( "Find a monster by its identifier" )
    public ResponseEntity< Monster > getById( @PathVariable( value = "id" ) Long monsterId ) {
        return new ResponseEntity<>( service.findById( monsterId ), HttpStatus.OK );
    }

    @PostMapping( "" )
    @ApiOperation( "Create a monster" )
    public ResponseEntity< Monster > create( @RequestBody Monster monster ) {
        return new ResponseEntity<>( service.create( monster ), HttpStatus.CREATED );
    }

    @PutMapping( "" )
    @ApiOperation( "Update a monster" )
    public ResponseEntity< Monster > update( @RequestBody Monster monster ) {
        return new ResponseEntity<>( service.update( monster ), HttpStatus.OK );
    }

    @DeleteMapping( "" )
    @ApiOperation( "Delete a monster" )
    public ResponseEntity< HttpStatus > delete( @RequestHeader Long monsterId ) {
        service.delete( monsterId );
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
}
