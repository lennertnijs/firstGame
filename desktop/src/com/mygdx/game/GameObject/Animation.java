package com.mygdx.game.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Animation {

    private final List<Frame> frames;
    private final float durationInMillis;

    public Animation(List<Frame> frames, float durationInMillis){
        this.frames = validateAndCopyFrames(frames);
        if(durationInMillis <= 0) {
            throw new IllegalArgumentException("Duration is negative or zero.");
        }
        this.durationInMillis = durationInMillis;
    }

    private List<Frame> validateAndCopyFrames(List<Frame> frames){
        Objects.requireNonNull(frames, "List is null.");
        if(frames.contains(null)) {
            throw new NullPointerException("List contains null.");
        }
        if(frames.size() == 0) {
            throw new IllegalArgumentException("List contains no elements.");
        }
        return new ArrayList<>(frames);
    }

    /**
     * Loops around.
     */
    public Frame getFrame(double deltaInMillis){
        if(deltaInMillis < 0) {
            throw new IllegalArgumentException("Delta is negative.");
        }
        float frameLength = durationInMillis / frames.size();
        int index = (int) (deltaInMillis % durationInMillis / frameLength);
        return frames.get(index);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Animation temp))
            return false;
        return frames.equals(temp.frames) && durationInMillis == temp.durationInMillis;
    }

    @Override
    public int hashCode(){
        return frames.hashCode() * 31 + Float.hashCode(durationInMillis);
    }

    @Override
    public String toString(){
        return String.format("Animation[frames=%s, durationInMillis=%f]", frames, durationInMillis);
    }
}
