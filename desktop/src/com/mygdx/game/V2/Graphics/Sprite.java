package com.mygdx.game.V2.Graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.Util.Dimensions;
import com.mygdx.game.V2.Util.Point;

import java.util.Objects;

/**
 * Represents a Sprite for drawing.
 * Sprites have a texture, a global position & dimensions.
 */
public final class Sprite {

    private TextureRegion texture;
    private Point position;
    private Dimensions dimensions;

    /**
     * Creates a new MUTABLE {@link Sprite}.
     * @param texture The texture. Cannot be null.
     * @param position The position. Cannot be null.
     * @param dimensions The dimensions. Cannot be null.
     */
    public Sprite(TextureRegion texture, Point position, Dimensions dimensions){
        Objects.requireNonNull(texture, "Texture is null.");
        Objects.requireNonNull(position, "Position is null.");
        Objects.requireNonNull(dimensions, "Dimensions is null.");
        this.texture = texture;
        this.position = position;
        this.dimensions = dimensions;
    }

    public TextureRegion getTexture(){
        return texture;
    }

    public Point getPosition(){
        return position;
    }

    public Dimensions getDimensions(){
        return dimensions;
    }

    /**
     * Sets the texture of this sprite to the given {@link TextureRegion}.
     * @param texture The texture. Cannot be null.
     */
    public void setTexture(TextureRegion texture){
        Objects.requireNonNull(texture, "Texture is null.");
        this.texture = texture;
    }

    /**
     * Sets the position of this sprite to the given {@link Point}.
     * @param position The position. Cannot be null.
     */
    public void setPosition(Point position){
        Objects.requireNonNull(position, "Position is null.");
        this.position = position;
    }

    /**
     * Sets the dimensions of this sprite to the given {@link Dimensions}.
     * @param dimensions The dimensions. Cannot be null.
     */
    public void setDimensions(Dimensions dimensions){
        Objects.requireNonNull(dimensions, "Dimensions is null.");
        this.dimensions = dimensions;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof  Sprite))
            return false;
        Sprite sprite = (Sprite) other;
        return texture.equals(sprite.texture)
                && position.equals(sprite.position)
                && dimensions.equals(sprite.dimensions);
    }

    @Override
    public int hashCode(){
        int result = texture.hashCode();
        result = result * 31 + position.hashCode();
        result = result * 31 + dimensions.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Sprite[texture=%s, position=%s, dimensions=%s]", texture, position, dimensions);
    }
}
