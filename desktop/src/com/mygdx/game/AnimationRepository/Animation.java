package com.mygdx.game.AnimationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Animation{

    private final List<IFrame> frames;
    private final float durationInMillis;

    public Animation(List<IFrame> frames, float durationInMillis){
        Objects.requireNonNull(frames, "List is null.");
        if(frames.contains(null)) {
            throw new NullPointerException("List contains null.");
        }
        if(frames.size() == 0) {
            throw new IllegalArgumentException("List contains no elements.");
        }
        this.frames = new ArrayList<>(frames);
        if(durationInMillis <= 0) {
            throw new IllegalArgumentException("Duration is negative or zero.");
        }
        this.durationInMillis = durationInMillis;
    }

    /**
     * Loops around.
     */
    public IFrame getFrame(double deltaInMillis){
        if(deltaInMillis < 0) {
            throw new IllegalArgumentException("Delta is negative.");
        }
        float frameLength = durationInMillis / frames.size();
        int index = (int) (deltaInMillis % durationInMillis / frameLength);
        return frames.get(index);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Animation))
            return false;
        Animation animation = (Animation) other;
        return frames.equals(animation.frames) && durationInMillis == animation.durationInMillis;
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
