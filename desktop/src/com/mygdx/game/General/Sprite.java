package com.mygdx.game.General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;

import java.util.Objects;

public final class Sprite{

    private TextureRegion texture;
    private Point anchor;
    private Vector vector;
    private Dimensions dimensions;
    private String map;

    public Sprite(TextureRegion texture, Point anchor, Vector vector, Dimensions dimensions, String map){
        this.texture = Objects.requireNonNull(texture, "Texture region is null.");
        this.anchor = Objects.requireNonNull(anchor, "Anchor is null.");
        this.vector = Objects.requireNonNull(vector, "Vector is null.");
        this.dimensions = Objects.requireNonNull(dimensions, "Dimensions is null.");
        this.map = Objects.requireNonNull(map, "Map is null.");
    }

    public Sprite(TextureRegion texture, Point anchor, Dimensions dimensions, String map){
        this.texture = Objects.requireNonNull(texture, "Texture region is null.");
        this.anchor = Objects.requireNonNull(anchor, "Anchor is null.");
        this.vector = new Vector(0, 0);
        this.dimensions = Objects.requireNonNull(dimensions, "Dimensions is null.");
        this.map = Objects.requireNonNull(map, "Map is null.");
    }

    public Sprite(TextureRegion texture, Point anchor, String map){
        this.texture = Objects.requireNonNull(texture, "Texture region is null.");
        this.anchor = Objects.requireNonNull(anchor, "Anchor is null.");
        this.vector = new Vector(0, 0);
        this.dimensions = new Dimensions(texture.getRegionWidth(), texture.getRegionHeight());
        this.map = Objects.requireNonNull(map, "Map is null.");
    }

    public TextureRegion getTexture(){
        return texture;
    }

    public void setTexture(TextureRegion t){
        this.texture = t;
    }

    public Point getAnchor(){
        return anchor;
    }

    public Location getLocation(){
        return new Location(map, anchor);
    }

    public void setLocation(Location location){
        this.anchor = Objects.requireNonNull(location, "Location is null.").position();
        this.map = location.mapName();
    }

    public Dimensions getDimensions(){
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions){
        this.dimensions = dimensions;
    }

    public Point getPosition(){
        return new Point(anchor.x() + vector.x(), anchor.y() + vector.y()) ;
    }

    public void setPosition(Point p) {
        int vector_x = p.x() - anchor.x();
        int vector_y = p.y() - anchor.y();
        this.vector = new Vector(vector_x, vector_y);
        System.out.println(this.vector);
    }
}
