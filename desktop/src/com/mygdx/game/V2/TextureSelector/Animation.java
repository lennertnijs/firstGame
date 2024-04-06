package com.mygdx.game.V2.TextureSelector;

import java.util.Arrays;
import java.util.Objects;

public final class Animation<T> {

    private final T[] frames;
    private final float duration;

    public Animation(T[] frames, float animationDuration){
        Objects.requireNonNull(frames, "Cannot create an Animation with null as frames.");
        if(Arrays.stream(frames).anyMatch(Objects::isNull))
            throw new NullPointerException("Cannot create an Animation with a null frame.");
        if(animationDuration <= 0)
            throw new IllegalArgumentException("Cannot create an Animation with a negative or 0 duration.");
        this.frames = frames;
        this.duration = animationDuration;
    }

    public T[] getFrames(){
        return frames;
    }

    public float getDuration(){
        return duration;
    }

    /**
     * Returns the frame associated with the delta (in seconds).
     * @param delta The delta in seconds. Cannot be negative, or equal/bigger than the animation's duration.
     *
     * @return The frame
     */
    public T getFrame(float delta){
        if(delta < 0 || delta >= duration)
            throw new IllegalArgumentException("Cannot get a frame from an invalid delta.");
        float frameDuration = (duration / frames.length);
        int frameNumber = (int) (delta / frameDuration);
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
