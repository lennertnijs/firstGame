package com.mygdx.game.V2;

public final class Validator {

    public static void withinRange(int number, int lower, int upper){
        if(number < lower || number > upper){
            throw new IllegalArgumentException();
        }
    }

    public static void notNegative(int number){
        if(number < 0){
            throw new IllegalArgumentException();
        }
    }
}
