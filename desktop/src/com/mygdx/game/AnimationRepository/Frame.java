package com.mygdx.game.AnimationRepository;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Vector;

import java.util.Objects;

public final class Frame{

    private final TextureRegion textureRegion;
    /**
     * The translation vector. Used for when a Frame is not in the bottom-left corner of the animation.
     * The vector represents the translation from that bottom-left to the bottom-left of this frame's texture region.
     */
    private final Vector translation;
    private final Dimensions dimensions;

    private Frame(Builder builder){
        this.textureRegion = builder.textureRegion;
        this.translation = builder.translation;
        this.dimensions = builder.dimensions;
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

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private TextureRegion textureRegion = null;
        private Vector translation = null;
        private Dimensions dimensions = null;

        private Builder(){
        }

        public Builder textureRegion(TextureRegion textureRegion){
            this.textureRegion = textureRegion;
            return this;
        }

        public Builder translation(Vector translation){
            this.translation = translation;
            return this;
        }

        public Builder dimensions(Dimensions dimensions){
            this.dimensions = dimensions;
            return this;
        }

        public Frame build(){
            Objects.requireNonNull(textureRegion, "Texture region is null.");
            if(translation == null){
                this.translation = new Vector(0, 0);
            }
            if(dimensions == null){
                this.dimensions = new Dimensions(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
            }
            return new Frame(this);

        }
    }
}
