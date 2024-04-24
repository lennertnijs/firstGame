package com.mygdx.game.V2.TextureSelector;

/**
 * Represents a clock for frame/texture selection in animations.
 */
public final class AnimationClock {

    /**
     * The delta (in millis).
     */
    private float deltaInMillis;

    /**
     * Creates a new {@link AnimationClock} with a delta of 0.
     */
    public AnimationClock(){
        deltaInMillis = 0;
    }

    /**
     * @return The delta in millis.
     */
    public float delta(){
        return deltaInMillis;
    }

    /**
     * Increases this {@link AnimationClock}'s delta with the given value (in millis).
     * @param increaseInMillis The increase (in millis). Cannot be negative.
     *
     * @throws IllegalArgumentException If the increase is negative.
     */
    public void increase(long increaseInMillis){
        if(increaseInMillis < 0)
            throw new IllegalArgumentException("Increase is negative.");
        deltaInMillis += increaseInMillis;
    }

    /**
     * Sets this {@link AnimationClock}'s delta to 0.
     */
    public void reset(){
        deltaInMillis = 0;
    }

    /**
     * Compares this {@link AnimationClock} to the given object and returns true if they're equal. Returns false otherwise.
     * Two {@link AnimationClock}s are equal if they hold the same delta.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof AnimationClock))
            return false;
        AnimationClock clock = (AnimationClock) other;
        return deltaInMillis == clock.deltaInMillis;
    }

    /**
     * @return The hash code of this {@link AnimationClock}.
     */
    @Override
    public int hashCode(){
        return Float.hashCode(deltaInMillis);
    }

    /**
     * @return The string representation of this {@link AnimationClock}.
     */
    @Override
    public String toString(){
        return String.format("AnimationClock[deltaInMillis=%f]", deltaInMillis);
    }
}
