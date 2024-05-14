package com.mygdx.game.Util;

public enum Day {

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY;

    public Day next(){
        int index = this.ordinal();
        return Day.values()[(index + 1)%Day.values().length];
    }
}
