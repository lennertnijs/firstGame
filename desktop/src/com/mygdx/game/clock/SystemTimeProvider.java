package com.mygdx.game.clock;

public class SystemTimeProvider implements TimeProvider{

    private double last;

    public SystemTimeProvider(){
        this.last = System.currentTimeMillis();
    }

    public double update(){
        double delta = System.currentTimeMillis() - last;
        last = System.currentTimeMillis();
        return delta;
    }

    public void reset(){
        last = System.currentTimeMillis();
    }

    public SystemTimeProvider copy(){
        return new SystemTimeProvider();
    }
}
