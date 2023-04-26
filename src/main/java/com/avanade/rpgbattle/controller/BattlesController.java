package com.avanade.rpgbattle.controller;

import com.avanade.rpgbattle.enumeration.PlayerType;
import com.avanade.rpgbattle.model.Battle;
import com.avanade.rpgbattle.model.BattleLog;
import com.avanade.rpgbattle.model.BattleStatus;
import com.avanade.rpgbattle.model.Initiative;
import com.avanade.rpgbattle.service.BattleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping( value = "api/battles" )
@Api( value = "Battles", consumes = "application/json", produces = "application/json" )
@CrossOrigin( origins = "*" )
public class BattlesController {

    @Autowired
    private BattleService service;

    @PostMapping( "" )
    @ApiOperation( "Create a battle" )
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Return the created item"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< Battle > create( @Valid @RequestBody Battle battle ) {
        return new ResponseEntity<>( service.create( battle ), HttpStatus.CREATED );
    }

    @GetMapping( "" )
    @ApiOperation( "Find all battles" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return all items"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity<List<Battle>> getAll( ) {
        return new ResponseEntity<>( service.findAll( ), HttpStatus.OK );
    }

    @GetMapping( "{id}" )
    @ApiOperation( "Find a battle by its identifier" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return the item by its identifier"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< Battle > getById( @Valid @PathVariable( value = "id" ) @Min(1) Long battleId ) {
        return new ResponseEntity<>( service.findById( battleId ), HttpStatus.OK );
    }

    @PostMapping( "{id}/initiative" )
    @ApiOperation( "Define which player will start the battle" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return the item"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity<Initiative> initiative( @Valid @PathVariable( value = "id" ) @Min(1) Long battleId ) {
        return new ResponseEntity<>( service.initiative( battleId ), HttpStatus.OK );
    }

    @PostMapping( "{id}/attack" )
    @ApiOperation( "Perform an attack on your opponent in the battle" )
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Return the item"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< Integer > attack( @Valid @PathVariable( value = "id" ) @Min(1) Long battleId,
                                             @Valid @RequestBody @Min(1) int diceValue,
                                             @Valid @RequestBody PlayerType attacker) {
        return new ResponseEntity<>( service.attack( battleId, diceValue, attacker ), HttpStatus.OK );
    }

    @PostMapping( "{id}/defense" )
    @ApiOperation( "Perform a defense from your opponent in the battle" )
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Return the item"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< Integer > defense( @Valid @PathVariable( value = "id" ) @Min(1) Long battleId,
                                              @Valid @RequestBody @Min(1) int diceValue,
                                              @Valid @RequestBody PlayerType defender ) {
        return new ResponseEntity<>( service.defense( battleId, diceValue, defender ), HttpStatus.OK );
    }

    @GetMapping( "{id}/logs" )
    @ApiOperation( "Find all battle logs" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return the item by its identifier"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity<List<BattleLog>> getAllBattleLogs( @Valid @PathVariable( value = "id" ) @Min(1) Long battleId ) {
        return new ResponseEntity<>( service.findAllLogsByBattleId( battleId ), HttpStatus.OK );
    }

    @GetMapping( "{id}/status" )
    @ApiOperation( "Return the attributes from attacker and defender in the battle" )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the item"),
            @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
            @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
            @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< BattleStatus > status(@Valid @PathVariable( value = "id" ) @Min(1) Long battleId ) {
        return new ResponseEntity<>( service.status( battleId ), HttpStatus.OK );
    }
}
