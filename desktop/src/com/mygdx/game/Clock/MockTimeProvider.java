package com.mygdx.game.Clock;

public final class MockTimeProvider implements TimeProvider{

    private int mockValue;

    public MockTimeProvider(){
        mockValue = 0;
    }

    public double update(){
        mockValue += 500d;
        return 500d;
    }

    public void reset(){
        mockValue = 0;
    }
}
