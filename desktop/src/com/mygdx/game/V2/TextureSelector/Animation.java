package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Animation{

    private final List<TextureRegion> frames;
    private final float durationInMillis;

    public Animation(List<TextureRegion> frames, float durationInMillis){
        Objects.requireNonNull(frames, "List is null.");
        if(frames.contains(null))
            throw new NullPointerException("List contains null.");
        if(durationInMillis <= 0)
            throw new IllegalArgumentException("Duration is negative or zero.");
        this.frames = new ArrayList<>(frames);
        this.durationInMillis = durationInMillis;
    }

    public List<TextureRegion> frames(){
        return new ArrayList<>(frames);
    }

    public float duration(){
        return durationInMillis;
    }

    public TextureRegion getFrame(float delta){
        if(delta < 0)
            throw new IllegalArgumentException("Delta is negative.");
        float frameLength = durationInMillis / frames.size();
        int index = (int) (delta % durationInMillis / frameLength);
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
        int result = frames.hashCode();
        result = result * 31 + Float.hashCode(durationInMillis);
        return result;
    }

    @Override
    public String toString(){
        return String.format("Animation[frames=%s, durationInMillis=%f]", frames, durationInMillis);
    }
}
