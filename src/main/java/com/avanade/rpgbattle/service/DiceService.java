package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.model.Dice;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class DiceService {

    public int throwDices( @Valid Dice dice ) {

        int result = 0;

        int maximumNumberOfFaces = dice.getNumberOfFaces();
        int minimumNumberOfFaces = 1;

        for (int i = 0; i < dice.getQuantityOfDices(); i++)
        {
            result += (int)Math.floor(Math.random() * (maximumNumberOfFaces - minimumNumberOfFaces + 1) + minimumNumberOfFaces);
        }

        return result;
    }
}
