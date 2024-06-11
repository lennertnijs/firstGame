package com.mygdx.game.UtilMethods;

import java.util.Random;

public class RandomNumberGenerator {

    private static final Random random = new Random(123456789);

    public RandomNumberGenerator(){

    }

    public static int generate(int limit){
        return random.nextInt(limit);
    }

    public static int generateBetween(int bot, int top){
        return random.nextInt(bot, top);
    }
}
