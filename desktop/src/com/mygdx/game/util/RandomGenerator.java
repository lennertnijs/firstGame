package com.mygdx.game.util;

import java.util.Random;

public class RandomGenerator {

    private static final Random random = new Random(123456789);

    public RandomGenerator(){

    }

    public static int generate(int limit){
        return random.nextInt(limit);
    }

    public static int generateBetween(int bot, int top){
        return random.nextInt(bot, top);
    }

    public static Vec2 generateAround(Vec2 p, int minAmount, int maxAmount){
        int x_diff = generateBetween(-maxAmount, maxAmount);
        int y_diff = generateBetween(-maxAmount, maxAmount);
        int distanceToNewPoint = p.distanceTo(new Vec2(p.x() + x_diff, p.y() + y_diff));
        while(distanceToNewPoint < minAmount || distanceToNewPoint > maxAmount){
            x_diff = generateBetween(-maxAmount, maxAmount);
            y_diff = generateBetween(-maxAmount, maxAmount);
            distanceToNewPoint = p.distanceTo(new Vec2(p.x() + x_diff, p.y() + y_diff));
        }
        return new Vec2(p.x() + x_diff, p.y() + y_diff);
    }
}
