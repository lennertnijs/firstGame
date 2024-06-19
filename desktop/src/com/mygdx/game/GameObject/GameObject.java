package com.mygdx.game.GameObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HitBox.HitBox;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;
import com.mygdx.game.HitBox.Rectangle;

import java.util.Objects;

public class GameObject {

    private final TextureRegion textureRegion;
    private final Point position;
    private final Dimensions dimensions;
    private String map;

    public GameObject(TextureRegion textureRegion, Point position, Dimensions dimensions, String map){
        this.textureRegion = Objects.requireNonNull(textureRegion);
        this.position = Objects.requireNonNull(position, "Position is null.");
        this.dimensions = Objects.requireNonNull(dimensions, "Dimensions is null.");
        this.map = Objects.requireNonNull(map, "Map is null.");
    }

    protected GameObject(Point position, Dimensions dimensions, String map){
        this.textureRegion = null;
        this.position = Objects.requireNonNull(position, "Position is null.");
        this.dimensions = Objects.requireNonNull(dimensions, "Dimensions is null.");
        this.map = Objects.requireNonNull(map, "Map is null.");
    }

    public TextureRegion getTexture(){
        return textureRegion;
    }

    public Point getPosition(){
        return position;
    }

    public Dimensions getDimensions(){
        return dimensions;
    }

    public final String getMap(){
        return map;
    }

    public final void setMap(String map){
        this.map = Objects.requireNonNull(map, "Map is null.");
    }

    public HitBox getHitBox(){
        return new Rectangle(position, dimensions);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof GameObject))
            return false;
        GameObject object = (GameObject) other;
        return  Objects.equals(textureRegion, object.textureRegion) &&
                position.equals(object.position) &&
                dimensions.equals(object.dimensions) &&
                map.equals(object.map);
    }

    @Override
    public int hashCode(){
        int result = textureRegion == null ? 0 : textureRegion.hashCode();
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
