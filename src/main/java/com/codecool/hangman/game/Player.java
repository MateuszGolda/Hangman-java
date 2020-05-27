package com.codecool.hangman.game;

public class Player {
    private int hp;
    private String name;

    Player(int hp) {
        this.hp = hp;
    }

    int getHp() {
        return hp;
    }

    void decreaseHp(int damage) {
        hp -= damage;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
