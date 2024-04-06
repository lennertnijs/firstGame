package com.mygdx.game.V2.TextureSelector;

import java.util.Arrays;

public final class Animation<T> {

    private final T[] frames;
    private final float duration;

    public Animation(T[] frames, float animationDuration){
        this.frames = frames;
        this.duration = animationDuration;
    }

    public T[] getFrames(){
        return frames;
    }

    public float getDuration(){
        return duration;
    }

    public T getFrame(float delta){
        if(delta < 0 || delta > duration)
            throw new IllegalArgumentException("Cannot get a frame from an invalid delta.");
        float frameDuration = (duration / frames.length);
        int frameNumber = (int) Math.ceil(delta / frameDuration);
        return frames[frameNumber];
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Animation<?>))
            return false;
        Animation<?> animation = (Animation<?>) other;
        return Arrays.equals(frames, animation.frames) &&
                duration == animation.duration;
    }

    @Override
    public int hashCode(){
        int result = Arrays.hashCode(frames);
        result = result * 31 + (int) duration;
        return result;
    }

    @Override
    public String toString(){
        return String.format("Animation[Frames=%s, Duration=%f]", Arrays.toString(frames), duration);
    }
}
