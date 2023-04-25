package com.avanade.rpgbattle.controller;

import com.avanade.rpgbattle.service.SampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "api/samples" )
@Api( value = "Sample" )
@CrossOrigin( origins = "*" )
public class SampleController {

    @Autowired
    private SampleService service;

    @GetMapping( "" )
    @ApiOperation( "Return just a sample static message" )
    public ResponseEntity<String> sample( ) {
        return new ResponseEntity<>( "This is the sample message", HttpStatus.OK );
    }
}
