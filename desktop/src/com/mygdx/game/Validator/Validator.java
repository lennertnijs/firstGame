package com.mygdx.game.Validator;

import java.util.List;
import java.util.Objects;

public class Validator {

    public static void notNull(Object o, String msg){
        try{
            Objects.requireNonNull(o);
        }catch(NullPointerException n){
            throw new IllegalArgumentException(msg);
        }
    }

    public static void notNegative(int number, String msg){
        if(number < 0){
            throw new IllegalArgumentException(msg);
        }
    }

    public static void notNullAndNotContainsNull(List<?> objects, String msg){
        try{
            Objects.requireNonNull(objects);
            for(Object o : objects){
                Objects.requireNonNull(o);
            }
        }catch(NullPointerException n){
            throw new IllegalArgumentException(msg);
        }
    }
}
