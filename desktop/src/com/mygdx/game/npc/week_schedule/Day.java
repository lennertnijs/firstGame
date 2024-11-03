package com.mygdx.game.npc.week_schedule;

public enum Day {

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY;

    public Day next(){
        int index = this.ordinal();
        int nextIndex = (index + 1) % Day.values().length;
        return Day.values()[nextIndex];
    }
}
