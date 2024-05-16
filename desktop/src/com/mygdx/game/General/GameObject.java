package com.mygdx.game.General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Rectangle;

import java.util.Objects;

public class GameObject {

    private TextureRegion textureRegion;
    private Point position;
    private Dimensions dimensions;
    private String map;

    public GameObject(TextureRegion textureRegion, Point position, Dimensions dimensions, String map){
        this.textureRegion = Objects.requireNonNull(textureRegion, "Texture region is null.");
        this.position = Objects.requireNonNull(position, "Position is null.");
        this.dimensions = Objects.requireNonNull(dimensions, "Dimensions is null.");
        this.map = Objects.requireNonNull(map, "Map is null.");
    }

    public final TextureRegion getTexture(){
        return textureRegion;
    }

    public final void setTexture(TextureRegion textureRegion){
        this.textureRegion = Objects.requireNonNull(textureRegion, "Texture region is null.");
    }

    public Point getPosition(){
        return position;
    }

    public void setPosition(Point position){
        this.position = Objects.requireNonNull(position, "Position is null.");
    }

    public final Dimensions getDimensions(){
        return dimensions;
    }

    public final void setDimensions(Dimensions dimensions){
        this.dimensions = Objects.requireNonNull(dimensions, "Dimensions is null.");
    }

    public final String getMap(){
        return map;
    }

    public final void setMap(String map){
        this.map = Objects.requireNonNull(map, "Map is null.");
    }

    public Rectangle getHitBox(){
        return new Rectangle(position, dimensions);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof GameObject))
            return false;
        GameObject gameObject = (GameObject) other;
        return textureRegion.equals(gameObject.textureRegion) && position.equals(gameObject.position) &&
                dimensions.equals(gameObject.dimensions) && map.equals(gameObject.map);
    }

    @Override
    public int hashCode(){
        int result = textureRegion.hashCode();
        result = result * 31 + position.hashCode();
        result = result * 31 + dimensions.hashCode();
        result = result * 31 + map.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("GameObject[textureRegion=%s, position=%s, dimensions=%s, map=%s]",
                textureRegion, position, dimensions, map);
    }
}
