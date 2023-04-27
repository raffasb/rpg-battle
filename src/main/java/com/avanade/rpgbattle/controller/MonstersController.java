package com.avanade.rpgbattle.controller;

import com.avanade.rpgbattle.model.Monster;
import com.avanade.rpgbattle.service.MonsterService;
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
@RequestMapping( value = "api/monsters" )
@Api( value = "Monster", consumes = "application/json", produces = "application/json" )
@CrossOrigin( origins = "*" )
public class MonstersController {

    @Autowired
    private MonsterService service;

    @GetMapping( "" )
    @ApiOperation( "Find all monsters" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return all items"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity<List< Monster >> getAll( ) {
        return new ResponseEntity<>( service.findAll( ), HttpStatus.OK );
    }

    @GetMapping( "{id}" )
    @ApiOperation( "Find a monster by its identifier" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return the item by its identifier"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< Monster > getById( @Valid @PathVariable( value = "id" ) @Min(1) Long monsterId ) {
        return new ResponseEntity<>( service.findById( monsterId ), HttpStatus.OK );
    }

    @PostMapping( "" )
    @ApiOperation( "Create a monster" )
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Return the created item"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< Monster > create( @Valid @RequestBody Monster monster ) {
        return new ResponseEntity<>( service.create( monster ), HttpStatus.CREATED );
    }

    @PutMapping( "" )
    @ApiOperation( "Update a monster" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return the item"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< Monster > update( @Valid @RequestBody Monster monster ) {
        return new ResponseEntity<>( service.update( monster ), HttpStatus.OK );
    }

    @DeleteMapping( "{id}" )
    @ApiOperation( "Delete a monster" )
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "No content to return"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< HttpStatus > delete( @Valid @PathVariable( value = "id" ) @Min(1) Long monsterId ) {
        service.delete( monsterId );
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
}