package com.mygdx.game.GameObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HitBox.HitBox;
import com.mygdx.game.HitBox.Rectangle;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.UpdatedUtil.Vec2;

import java.util.Objects;

public class GameObject {

    protected final Transform transform;
    protected final Renderer renderer;
    protected String map;

    public GameObject(Transform transform, Renderer renderer, String map){
        this.transform = Objects.requireNonNull(transform);
        this.renderer = Objects.requireNonNull(renderer);
        this.map = Objects.requireNonNull(map);
    }

    public Vec2 getPosition(){
        return transform.getPosition().add(renderer.getOffSet());
    }

    public void setPosition(Vec2 position){
        transform.setPosition(position);
    }

    public void updateDelta(double delta){
        renderer.update(delta);
    }

    public void setDirection(Direction direction){
        renderer.setDirection(direction);
    }

    public void setActivity(String activity){
        renderer.setActivity(activity);
    }

    public TextureRegion getTexture(){
        return renderer.texture();
    }

    public Dimensions getDimensions(){
        return new Dimensions(renderer.getWidth(), renderer.getHeight());
    }

    public final String getMap(){
        return map;
    }

    public final void setMap(String map){
        this.map = map;
    }

    public HitBox getHitBox(){
        return new Rectangle(transform.getPosition(), new Dimensions(renderer.getWidth(), renderer.getHeight()));
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof GameObject object))
            return false;
        return  map.equals(object.map);
    }

    @Override
    public int hashCode(){
        return map.hashCode();
    }

    @Override
    public String toString(){
        return String.format("GameObject[map=%s]", map);
    }
}
