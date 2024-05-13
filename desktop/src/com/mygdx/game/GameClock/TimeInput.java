package com.mygdx.game.GameClock;

public class TimeInput {

    float last;
    public TimeInput(){
        last = System.currentTimeMillis();
    }

    public float update(){
        return 50f;
    }
}
