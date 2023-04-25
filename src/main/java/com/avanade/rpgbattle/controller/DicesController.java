package com.avanade.rpgbattle.controller;

import com.avanade.rpgbattle.model.Dice;
import com.avanade.rpgbattle.service.DiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping( value = "api/dices" )
@Api( value = "Dices", consumes = "application/json", produces = "application/json" )
@CrossOrigin( origins = "*" )
public class DicesController {

    @Autowired
    private DiceService service;

    @PostMapping( "throw" )
    @ApiOperation( "Throw one or more dices" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return the item"),
        @ApiResponse(code = 400, message = "Something went wrong on the informed payload"),
        @ApiResponse(code = 403, message = "You do not have permissions to access this resource"),
        @ApiResponse(code = 500, message = "Something went wrong on the server. Please contact your administrator"),
    })
    public ResponseEntity<Integer> throwDices(@Valid @RequestBody Dice dice) {
        return new ResponseEntity<>( service.throwDices( dice ), HttpStatus.OK );
    }
}

