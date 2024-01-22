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
}
