package com.mygdx.game.V2.TextureSelector;

import java.util.Objects;

public final class DeltaTime {

    private float delta;
    private long previousSystemTime;
    private final TimeProvider timeProvider;

    private DeltaTime(TimeProvider timeProvider){
        delta = 0;
        previousSystemTime = timeProvider.getTimeInMillis();
        this.timeProvider = timeProvider;
    }

    public static DeltaTime create(TimeProvider timeProvider){
        Objects.requireNonNull(timeProvider, "Cannot create a DeltaTime with a null TimeProvider.");
        return new DeltaTime(timeProvider);
    }

    /**
     * Updates the delta and returns it (in seconds).
     *
     * @return The delta in seconds.
     */
    public float getDelta(){
        update();
        return delta;
    }

    private void update(){
        long currentSystemTime = timeProvider.getTimeInMillis();
        delta += currentSystemTime - previousSystemTime;
        previousSystemTime = currentSystemTime;
    }

    @Override
    public String toString(){
        return String.format("DeltaTime[Delta=%f, PreviousSystemTime=%d]", delta, previousSystemTime);
    }
}
