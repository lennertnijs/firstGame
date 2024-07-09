package com.mygdx.game.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HitBox.HitBox;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Vector;

import java.util.Objects;

/**
 * Represents a single frame in an animation.
 */
public final class Frame{

    /**
     * The (part of the) texture of the frame.
     */
    private final TextureRegion textureRegion;
    /**
     * The translation vector of the texture.
     * Should be added to the position of the object to correctly display the texture.
     */
    private final Vector textureTranslation;
    /**
     * The dimensions of the texture.
     */
    private final Dimensions dimensions;
    /**
     * The damage hit box of this frame. Optional field.
     * Applicable in cases where certain frames need a smaller part of the frame's texture to work as the part damaging.
     */
    private final HitBox damageBox;
    /**
     * The translation vector of the damage hit box's.
     * Should be added to the position of the object to correctly create the damage area.
     */
    private final Vector damageBoxTranslation;

    /**
     * Creates and returns an IMMUTABLE {@link Frame} for animation use.
     * @param builder The builder. Cannot be null.
     */
    private Frame(Builder builder){
        this.textureRegion = builder.textureRegion;
        this.textureTranslation = builder.textureTranslation;
        this.dimensions = builder.dimensions;
        this.damageBox = builder.damageBox;
        this.damageBoxTranslation = builder.damageBoxTranslation;
    }

    /**
     * @return The texture
     */
    public TextureRegion textureRegion(){
        return textureRegion;
    }

    /**
     * @return The texture's translation vector.
     */
    public Vector textureTranslation(){
        return textureTranslation;
    }

    /**
     * @return The texture's dimensions.
     */
    public Dimensions dimensions(){
        return dimensions;
    }

    /**
     * @return The damage hit box. Null, if not set.
     */
    public HitBox damageBox(){
        return damageBox;
    }

    /**
     * @return The damage hit box's translation vector.
     */
    public Vector damageBoxTranslation(){
        return damageBoxTranslation;
    }

    /**
     * @return The string representation.
     */
    @Override
    public String toString(){
        return String.format("Frame[textureRegion=%s, textureTranslation=%s, dimensions=%s, " +
                        "damageBox=%s, damageBoxTranslation=%s]",
                textureRegion, textureTranslation, dimensions, damageBox, damageBoxTranslation);
    }

    /**
     * @return A new builder to build {@link Frame}s with.
     */
    public static Builder builder(){
        return new Builder();
    }

    /**
     * Internal builder class for valid {@link Frame} building.
     */
    public static class Builder{

        /**
         * The texture region of the frame.
         * REQUIRED
         */
        private TextureRegion textureRegion = null;
        /**
         * The translation vector of the texture.
         * OPTIONAL
         * DEFAULT VALUE: a 0 vector
         */
        private Vector textureTranslation = new Vector(0, 0);
        /**
         * The dimensions of the texture.
         * OPTIONAL
         * DEFAULT VALUE: the texture region's dimensions
         */
        private Dimensions dimensions = null;

        private float dimensionScale = 1;
        /**
         * The damage hit box of the frame.
         * OPTIONAL
         * DEFAULT VALUE: null
         */
        private HitBox damageBox = null;
        /**
         * The translation vector of the damage hit box of the frame.
         * OPTIONAL
         * DEFAULT VALUE: a 0 vector
         */
        private Vector damageBoxTranslation = new Vector(0, 0);

        private Builder(){
        }

        public Builder textureRegion(TextureRegion textureRegion){
            this.textureRegion = textureRegion;
            return this;
        }

        public Builder textureTranslation(Vector translation){
            this.textureTranslation = translation;
            return this;
        }

        public Builder dimensions(Dimensions dimensions){
            this.dimensions = dimensions;
            return this;
        }

        public Builder dimensionsScale(float scale){
            this.dimensionScale = scale;
            return this;
        }

        public Builder damageBox(HitBox damageBox){
            this.damageBox = damageBox;
            return this;
        }

        public Builder damageBoxTranslation(Vector translation){
            this.damageBoxTranslation = translation;
            return this;
        }

        /**
         * Validates all the builder's fields and returns a valid {@link Frame} if the fields were satisfactory.
         * More specifically:
         * - The texture region cannot be null.
         * - The texture translation vector cannot have been set to null.
         * - The damage box translation vector cannot have been set to null.
         *
         * @return The {@link Frame}.
         */
        public Frame build(){
            Objects.requireNonNull(textureRegion, "Texture region is null.");
            Objects.requireNonNull(textureTranslation, "Texture translation vector is null.");
            if(dimensionScale <= 0){
                throw new IllegalArgumentException("Dimension scale is negative or 0.");
            }
            if(dimensions == null){
                int width = (int)(textureRegion.getRegionWidth() * dimensionScale);
                int height = (int)(textureRegion.getRegionHeight() * dimensionScale);
                this.dimensions = new Dimensions(width, height);
            }
            Objects.requireNonNull(damageBoxTranslation, "Damage box translation vector is null.");
            return new Frame(this);
        }
    }
}
