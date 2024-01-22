package com.mygdx.game.NPC;

public enum Day{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public Day next(){
        for(int i = 0; i < Day.values().length;i++){
            if(Day.values()[i].equals(this)){
                return Day.values()[(i+1)%7];
            }
        }
        throw new IllegalArgumentException("Cannot find the correct day");
    }
}
