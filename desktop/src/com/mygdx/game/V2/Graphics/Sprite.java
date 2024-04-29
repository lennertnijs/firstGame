package com.mygdx.game.V2.Graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.Util.Dimensions;
import com.mygdx.game.V2.Util.Point;

import java.util.Objects;

public final class Sprite {

    private TextureRegion texture;
    private Point position;
    private Dimensions dimensions;

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

    public void setTexture(TextureRegion texture){
        Objects.requireNonNull(texture, "Texture is null.");
        this.texture = texture;
    }

    public Point getPosition(){
        return position;
    }

    public void setPosition(Point position){
        Objects.requireNonNull(position, "Position is null.");
        this.position = position;
    }

    public Dimensions getDimensions(){
        return dimensions;
    }

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
