package com.avanade.rpgbattle.controller;

import com.avanade.rpgbattle.model.Hero;
import com.avanade.rpgbattle.service.HeroService;
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
@RequestMapping( value = "api/heroes" )
@Api( value = "Heroes", consumes = "application/json", produces = "application/json" )
@CrossOrigin( origins = "*" )
public class HeroesController {

    @Autowired
    private HeroService service;

    @GetMapping( "" )
    @ApiOperation( "Find all heroes" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return all items"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity<List<Hero>> getAll( ) {
        return new ResponseEntity<>( service.findAll( ), HttpStatus.OK );
    }

    @GetMapping( "{id}" )
    @ApiOperation( "Find a hero by its identifier" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return the item by its identifier"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< Hero > getById( @Valid @PathVariable( value = "id" ) @Min(1) Long heroId ) {
        return new ResponseEntity<>( service.findById( heroId ), HttpStatus.OK );
    }

    @PostMapping( "" )
    @ApiOperation( "Create a hero" )
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Return the created item"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< Hero > create( @Valid @RequestBody Hero hero ) {
        return new ResponseEntity<>( service.create( hero ), HttpStatus.CREATED );
    }

    @PutMapping( "" )
    @ApiOperation( "Update a hero" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return the item"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< Hero > update( @Valid @RequestBody Hero hero ) {
        return new ResponseEntity<>( service.update( hero ), HttpStatus.OK );
    }

    @DeleteMapping( "" )
    @ApiOperation( "Delete a hero" )
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "No content to return"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity< HttpStatus > delete( @Valid @RequestHeader Long heroId ) {
        service.delete( heroId );
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
}