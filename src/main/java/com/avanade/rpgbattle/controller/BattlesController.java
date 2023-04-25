package com.avanade.rpgbattle.controller;

import com.avanade.rpgbattle.model.Battle;
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

    @PostMapping( "initiative" )
    @ApiOperation( "Define a initiative" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return the created item"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< Battle > initiative( @Valid @RequestBody Battle battle ) {
        return new ResponseEntity<>( service.create( battle ), HttpStatus.OK );
    }

//    @PutMapping( "" )
//    @ApiOperation( "Update a hero" )
//    public ResponseEntity< Hero > update( @RequestBody Hero hero ) {
//        return new ResponseEntity<>( service.update( hero ), HttpStatus.OK );
//    }
//
//    @DeleteMapping( "" )
//    @ApiOperation( "Delete a hero" )
//    public ResponseEntity< HttpStatus > delete( @RequestHeader Long heroId ) {
//        service.delete( heroId );
//        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
//    }
}
