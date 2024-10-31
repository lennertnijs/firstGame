package com.mygdx.game.game_object;

import java.util.Arrays;
import java.util.Objects;

/**
 * An immutable animation.
 * An animation contains:
 * - an array of frames
 * - a duration (in milliseconds)
 */
public final class Animation {

    private final Frame[] frames;
    private final float durationInMillis;
    private final float frameDurationInMillis;

    public Animation(Frame[] frames, float durationInMillis){
        Objects.requireNonNull(frames);
        if(frames.length == 0){
            throw new IllegalArgumentException("Animations should have at least 1 frame.");
        }
        for(Frame frame : frames){
            Objects.requireNonNull(frame);
        }
        if(durationInMillis <= 0) {
            throw new IllegalArgumentException("Duration cannot be negative or 0.");
        }
        this.frames = frames;
        this.durationInMillis = durationInMillis;
        this.frameDurationInMillis = durationInMillis / frames.length;
    }

    /**
     * Fetches the frame with the given delta (looping around) from the animation and returns it.
     * @param deltaInMillis The delta to fetch for. Cannot be negative.
     */
    public Frame getFrame(double deltaInMillis){
        if(deltaInMillis < 0) {
            throw new IllegalArgumentException("Delta is negative.");
        }
        int index = (int) (deltaInMillis % durationInMillis / frameDurationInMillis);
        return frames[index];
    }

    @Override
    public String toString(){
        return String.format("Animation[frames=%s, durationInMillis=%f]", Arrays.toString(frames), durationInMillis);
    }
}
