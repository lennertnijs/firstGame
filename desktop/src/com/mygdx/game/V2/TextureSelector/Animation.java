package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an animation.
 * An animation means frames and a duration (in milliseconds).
 */
public final class Animation{

    /**
     * The frames. (as {@link TextureRegion}s.
     */
    private final List<TextureRegion> frames;
    /**
     * The animation duration in milliseconds.
     */
    private final float durationInMillis;

    /**
     * Creates a new {@link Animation}.
     * This {@link Animation} is immutable, besides the fact that the underlying {@link TextureRegion} is mutable.
     * @param frames The list of frames. Cannot be null. Cannot contain null.
     * @param durationInMillis The duration in milliseconds. Cannot be negative or 0.
     */
    public Animation(List<TextureRegion> frames, float durationInMillis){
        Objects.requireNonNull(frames, "List is null.");
        if(frames.contains(null))
            throw new NullPointerException("List contains null.");
        if(durationInMillis <= 0)
            throw new IllegalArgumentException("Duration is negative or zero.");
        this.frames = new ArrayList<>(frames);
        this.durationInMillis = durationInMillis;
    }

    /**
     * @return The frames. (Note that the list has been copied defensively, but the {@link TextureRegion}s are the original.)
     */
    public List<TextureRegion> frames(){
        return new ArrayList<>(frames);
    }

    /**
     * @return The duration in millis.
     */
    public float duration(){
        return durationInMillis;
    }

    /**
     * Fetches and returns the {@link TextureRegion} for the given delta in millis.
     * The {@link Animation} will loop, so for a delta bigger than the duration, it will loop back to the start.
     * @param deltaInMillis The delta in milliseconds. Cannot be negative.
     *
     * @return The {@link TextureRegion}.
     */
    public TextureRegion getFrame(float deltaInMillis){
        if(deltaInMillis < 0)
            throw new IllegalArgumentException("Delta is negative.");
        float frameLength = durationInMillis / frames.size();
        int index = (int) (deltaInMillis % durationInMillis / frameLength);
        return frames.get(index);
    }

    /**
     * Compares this {@link Animation} to the given object and returns true if they're equal. Returns false otherwise.
     * Two {@link Animation}s are equal if they hold the same frames & have the same duration.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Animation))
            return false;
        Animation animation = (Animation) other;
        return frames.equals(animation.frames) && durationInMillis == animation.durationInMillis;
    }

    /**
     * @return The hash code of this {@link Animation}.
     */
    @Override
    public int hashCode(){
        int result = frames.hashCode();
        result = result * 31 + Float.hashCode(durationInMillis);
        return result;
    }

    /**
     * @return The string representation of this {@link Animation}.
     */
    @Override
    public String toString(){
        return String.format("Animation[frames=%s, durationInMillis=%f]", frames, durationInMillis);
    }
}
