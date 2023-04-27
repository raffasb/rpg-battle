package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.model.Dice;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class DiceService {

    public Integer throwDices( @Valid Dice dice )
    {
        Integer result = 0;

        Integer maximumNumberOfFaces = dice.getNumberOfFaces();
        Integer minimumNumberOfFaces = 1;

        for (int i = 0; i < dice.getQuantityOfDices(); i++)
        {
            result += (int)Math.floor(Math.random() * (maximumNumberOfFaces - minimumNumberOfFaces + 1) + minimumNumberOfFaces);
        }

        dice.setDiceValue(result);

        return result;
    }
}
