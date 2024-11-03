package com.mygdx.game.clock;

public final class MockTimeProvider implements TimeProvider{

    private int mockValue;

    public MockTimeProvider(){
        this.mockValue = 0;
    }

    private MockTimeProvider(int mockValue){
        this.mockValue = mockValue;
    }

    public double update(){
        mockValue += 500d;
        return 500d;
    }

    public void reset(){
        mockValue = 0;
    }

    public MockTimeProvider copy(){
        return new MockTimeProvider(mockValue);
    }
}
