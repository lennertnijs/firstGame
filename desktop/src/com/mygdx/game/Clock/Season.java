package com.mygdx.game.Clock;

import com.mygdx.game.Validator.Validator;

public class Season {

    private final SeasonName name;
    private final int length;

    private Season(SeasonName name, int length){
        this.name = name;
        this.length = length;
    }

    public static Season create(SeasonName name, int length){
        Validator.notNull(name, "Cannot create a Season with a null SeasonName.");
        Validator.notNegative(length, "Cannot create a Season with a negative length.");
        return new Season(name, length);
    }

    public SeasonName getName(){
        return name;
    }

    public int getLength(){
        return length;
    }


}
