package com.mygdx.game;

public class ArgumentValidator {
    public static void ifNullThrowError(Object o, String message){
        if(o == null){
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isNull(Object o){
        return o == null;
    }

    public static void ifIntegerIsNegativeThrowError(int number, String message){
        if(number < 0){
            throw new IllegalArgumentException(message);
        }
    }
}
