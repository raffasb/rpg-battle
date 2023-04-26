package com.avanade.rpgbattle.enumeration;

public enum CharacterType {
    Hero(1),
    Monster(2);

    private int characterType;

    private CharacterType(int characterType) {
        this.characterType = characterType;
    }

    public int getCharacterType() {
        return characterType;
    }
}
