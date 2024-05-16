package com.mygdx.game.General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.AnimationRepository.IFrame;
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


    private Sprite(Builder builder){
        this.texture = builder.textureRegion;
        this.anchor = builder.anchor;
        this.vector = builder.translation;
        this.dimensions = builder.dimensions;
        this.map = builder.map;
    }


    public TextureRegion getTexture(){
        return texture;
    }


    public Location getLocation(){
        return new Location(map, anchor);
    }

    public void setLocation(Location location){
        this.anchor = Objects.requireNonNull(location, "Location is null.").position();
        this.map = location.mapName();
    }

    public Point getPosition(){
        return new Point(anchor.x() + vector.x(), anchor.y() + vector.y()) ;
    }


    public void update(IFrame frame){
        Objects.requireNonNull(frame, "Frame is null.");
        this.texture = frame.textureRegion();
        this.dimensions = frame.dimensions();
        this.vector = frame.translation();
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private TextureRegion textureRegion;
        private Point anchor;
        private Vector translation;
        private Dimensions dimensions;
        private String map;

        private Builder(){
        }

        public Builder textureRegion(TextureRegion textureRegion){
            this.textureRegion = textureRegion;
            return this;
        }

        public Builder anchor(Point anchor){
            this.anchor = anchor;
            return this;
        }

        public Builder translation(Vector vector){
            this.translation = vector;
            return this;
        }

        public Builder dimensions(Dimensions dimensions){
            this.dimensions = dimensions;
            return this;
        }

        public Builder map(String map){
            this.map = map;
            return this;
        }

        public Sprite build(){
            Objects.requireNonNull(textureRegion, "Texture region is null.");
            Objects.requireNonNull(anchor, "Anchor point is null.");
            if(translation == null){
                translation = new Vector(0, 0);
            }
            if(dimensions == null){
                dimensions = new Dimensions(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
            }
            Objects.requireNonNull(map, "Map is null.");
            return new Sprite(this);
        }


    }
}
