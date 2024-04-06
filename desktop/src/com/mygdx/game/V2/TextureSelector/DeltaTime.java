package com.mygdx.game.V2.TextureSelector;

import java.util.Objects;

public final class DeltaTime implements IDeltaTime{

    private float delta;
    private long previousSystemTime;
    private final TimeProvider timeProvider;

    private DeltaTime(TimeProvider timeProvider){
        delta = 0;
        this.timeProvider = timeProvider;
        previousSystemTime = timeProvider.getTimeInMillis();
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
    @Override
    public float getDelta(){
        update();
        return delta/(float)1000;
    }

    @Override
    public void reset(){
        delta = 0;
        timeProvider.reset();
        previousSystemTime = timeProvider.getTimeInMillis();
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
