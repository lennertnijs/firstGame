package com.mygdx.game.V2.TextureSelector;

public class MockAnimation<T> implements IAnimation<T> {

    private final int mockValue;
    public MockAnimation(int mockValue){
        this.mockValue = mockValue;
    }

    public int getMockValue(){
        return mockValue;
    }

    @Override
    public T[] getFrames() {
        return null;
    }

    @Override
    public float getDuration() {
        return 0;
    }

    @Override
    public T getFrame(float delta) {
        return null;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof MockAnimation<?>))
            return false;
        MockAnimation<?> mockAnimation = (MockAnimation<?>) other;
        return mockValue == mockAnimation.mockValue;
    }

    @Override
    public int hashCode(){
        return mockValue;
    }
}
