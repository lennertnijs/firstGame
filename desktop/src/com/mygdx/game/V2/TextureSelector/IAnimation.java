package com.mygdx.game.V2.TextureSelector;

public interface IAnimation<T> {

    T[] getFrames();
    float getDuration();
    T getFrame(float delta);
    boolean equals(Object other);
    int hashCode();
    String toString();
}
