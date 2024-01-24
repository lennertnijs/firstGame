package com.mygdx.game.Clock;

// TESTED
public enum Day {

    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4),
    SATURDAY(5),
    SUNDAY(6);

    private final int index;

    Day(int index){
        this.index = index;
    }

    public Day next(){
        int nextIndex = this.index+1;
        int divisor = values().length;
        return values()[nextIndex%divisor];
    }
}
