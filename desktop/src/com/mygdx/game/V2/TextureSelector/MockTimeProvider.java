package com.mygdx.game.V2.TextureSelector;

public final class MockTimeProvider implements TimeProvider{

    private long mockValue;

    public MockTimeProvider(){
        mockValue = 0;
    }

    @Override
    public long getTimeInMillis(){
        return mockValue;
    }

    @Override
    public void reset(){
        this.mockValue = 0;
    }

    public void incrementMockValue(){
        mockValue += 1000;
    }
}
