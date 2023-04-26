package com.avanade.rpgbattle.enumeration;

public enum PlayerType {
    Player1(1),
    Player2(2);

    private int playerType;

    private PlayerType(int playerType) {
        this.playerType = playerType;
    }

    public int getPlayerType() {
        return playerType;
    }
}
