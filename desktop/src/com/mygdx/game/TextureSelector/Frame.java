package com.mygdx.game.TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Vector;

import java.util.Objects;

public final class Frame{

    private final TextureRegion textureRegion;

    // The translation vector representing how far from the bottom-left side of the outer bounding box of the sprite.
    private final Vector translation;
    private final Dimensions dimensions;

    public Frame(TextureRegion textureRegion, Vector translation, Dimensions dimensions){
        this.textureRegion = Objects.requireNonNull(textureRegion, "Texture region is null.");
        this.translation = Objects.requireNonNull(translation, "Translation Vector is null.");
        this.dimensions = Objects.requireNonNull(dimensions, "Dimensions is null.");
    }

    public Frame(TextureRegion textureRegion, Dimensions dimensions){
        this.textureRegion = Objects.requireNonNull(textureRegion, "Texture region is null.");
        this.translation = new Vector(0, 0);
        this.dimensions = Objects.requireNonNull(dimensions, "Dimensions is null.");
    }

    public TextureRegion textureRegion(){
        return textureRegion;
    }

    public Vector translation(){
        return translation;
    }

    public Dimensions dimensions(){
        return dimensions;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Frame))
            return false;
        Frame frame = (Frame) other;
        return textureRegion.equals(frame.textureRegion)
                && translation.equals(frame.translation)
                && dimensions.equals(frame.dimensions);
    }

    @Override
    public int hashCode(){
        int result = textureRegion.hashCode();
        result = result * 31 + translation.hashCode();
        result = result * 31 + dimensions.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Frame[textureRegion=%s, translation=%s, dimensions=%s]",
                textureRegion, translation, dimensions);
    }
}
