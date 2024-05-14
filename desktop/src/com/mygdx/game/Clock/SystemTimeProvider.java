package com.mygdx.game.Clock;

public class SystemTimeProvider implements TimeProvider{

    private double last;

    public SystemTimeProvider(){
        last = System.currentTimeMillis();
    }

    public double update(){
        double delta = System.currentTimeMillis() - last;
        last = System.currentTimeMillis();
        return delta;
    }

    public void reset(){
        last = System.currentTimeMillis();
    }
}
