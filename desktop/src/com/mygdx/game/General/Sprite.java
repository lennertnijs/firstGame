package com.mygdx.game.General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Point;

import java.util.Objects;

/**
 * Represents a Sprite for drawing.
 * Sprites have a texture, a global position & dimensions.
 */
public final class Sprite {

    private TextureRegion texture;
    private Location location;
    private Dimensions dimensions;

    /**
     * Creates a new MUTABLE {@link Sprite}.
     * @param texture The texture. Cannot be null.
     * @param location The location. Cannot be null.
     * @param dimensions The dimensions. Cannot be null.
     */
    public Sprite(TextureRegion texture, Location location, Dimensions dimensions){
        Objects.requireNonNull(texture, "Texture is null.");
        Objects.requireNonNull(location, "Position is null.");
        Objects.requireNonNull(dimensions, "Dimensions is null.");
        this.texture = texture;
        this.location = location;
        this.dimensions = dimensions;
    }

    public TextureRegion getTexture(){
        return texture;
    }

    public Location getLocation(){
        return location;
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
     * Sets the location of this sprite to the given {@link Point}.
     * @param location The location. Cannot be null.
     */
    public void setLocation(Location location){
        Objects.requireNonNull(location, "Position is null.");
        this.location = location;
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
                && location.equals(sprite.location)
                && dimensions.equals(sprite.dimensions);
    }

    @Override
    public int hashCode(){
        int result = texture.hashCode();
        result = result * 31 + location.hashCode();
        result = result * 31 + dimensions.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Sprite[texture=%s, location=%s, dimensions=%s]", texture, location, dimensions);
    }
}
