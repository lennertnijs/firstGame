package com.mygdx.game.V2;

import java.util.List;

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

    public static void notNull(Object o){
        if(o == null){
            throw new IllegalArgumentException();
        }
    }

    public static void notNullAndNotContainsNull(List<?> objects){
        if(objects == null || objects.contains(null)){
            throw new IllegalArgumentException();
        }
    }
}
