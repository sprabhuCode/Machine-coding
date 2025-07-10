package org.example.game.components;

import java.util.Random;

public class Dice {

    private static final int max = 6;
    private final Random random = new Random();

    public int roll(){
        return random.nextInt(max) +1;
    }
}
