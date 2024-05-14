package com.mygdx.game.GameClock;

public class TimeInput implements TimeProvider{

    private double last;

    public TimeInput(){
        last = System.currentTimeMillis();
    }

    public double getDelta(){
        return System.currentTimeMillis() - last;
    }

    public void update(){
        last = System.currentTimeMillis();
    }

    public void reset(){
        last = System.currentTimeMillis();
    }
}
