package com.mygdx.game.Clock;

public class SystemTimeProvider implements TimeProvider{

    private double last;

    public SystemTimeProvider(){
        this.last = System.currentTimeMillis();
    }

    private SystemTimeProvider(double last){
        this.last = last;
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
