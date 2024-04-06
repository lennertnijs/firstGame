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

    public void incrementMockValue(){
        mockValue++;
    }
}
