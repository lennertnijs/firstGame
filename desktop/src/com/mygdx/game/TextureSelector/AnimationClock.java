package com.mygdx.game.TextureSelector;

public final class AnimationClock {

    private float deltaInMillis;

    public AnimationClock(){
        deltaInMillis = 0;
    }

    public float getDeltaInMillis(){
        return deltaInMillis;
    }

    public void increase(long increaseInMillis){
        if(increaseInMillis < 0)
            throw new IllegalArgumentException("Increase is negative.");
        deltaInMillis += increaseInMillis;
    }

    public void reset(){
        deltaInMillis = 0;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof AnimationClock))
            return false;
        AnimationClock clock = (AnimationClock) other;
        return deltaInMillis == clock.deltaInMillis;
    }

    @Override
    public int hashCode(){
        return Float.hashCode(deltaInMillis);
    }

    @Override
    public String toString(){
        return String.format("AnimationClock[deltaInMillis=%f]", deltaInMillis);
    }

    public AnimationClock copy(){
        AnimationClock copy = new AnimationClock();
        copy.increase((long)this.deltaInMillis);
        return copy;
    }
}
