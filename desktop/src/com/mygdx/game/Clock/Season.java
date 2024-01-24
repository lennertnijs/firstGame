package com.mygdx.game.Clock;

// TESTED
public enum Season {

    LIGHT(0),
    DARK(1);

    private final int index;
    Season(int index){
        this.index = index;
    }

    public static Season getSeason(int index){
        return values()[index];
    }
    public Season next(){
        int nextIndex = this.index + 1;
        int divisor = values().length;
        return values()[nextIndex%divisor];
    }
}
