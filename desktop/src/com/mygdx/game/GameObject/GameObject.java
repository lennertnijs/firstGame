package com.mygdx.game.GameObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HitBox.HitBox;
import com.mygdx.game.Renderer.Animator;
import com.mygdx.game.Renderer.Renderer;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Point;
import com.mygdx.game.HitBox.Rectangle;

import java.util.Objects;

public class GameObject {

    private Point position;
    private String map;
    private Renderer renderer;

    public GameObject(Renderer renderer, Point position, String map){
        this.renderer = renderer;
        this.position = position;
        this.map = map;
    }

    public void updateDelta(double delta){
        if(renderer instanceof Animator){
            renderer.update(delta);
        }
    }

    public void setDirection(Direction direction){
        if(renderer instanceof Animator) {
            renderer.setDirection(direction);
        }
    }

    public void setActivity(String activity){
        if(renderer instanceof Animator) {
            renderer.setActivity(activity);
        }
    }

    public TextureRegion getTexture(){
        return renderer.getTexture();
    }

    public Point getPosition(){
        return position;
    }

    public void setPosition(Point position){
        this.position = Objects.requireNonNull(position);
    }

    public Dimensions getDimensions(){
        return renderer.getDimensions();
    }

    public final String getMap(){
        return map;
    }

    public final void setMap(String map){
        this.map = Objects.requireNonNull(map, "Map is null.");
    }

    public Location getLocation(){
        return new Location(map, position);
    }

    public void setLocation(Location location){
        Objects.requireNonNull(location, "Location is null.");
        this.map = location.map();
        this.position = location.position();
    }

    public HitBox getHitBox(){
        return new Rectangle(position, new Dimensions(50, 50));
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof GameObject object))
            return false;
        return  position.equals(object.position) &&
                map.equals(object.map);
    }

    @Override
    public int hashCode(){
        int result = position.hashCode();
        result = result * 31 + map.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("GameObject[position=%s, map=%s]", position, map);
    }
}
