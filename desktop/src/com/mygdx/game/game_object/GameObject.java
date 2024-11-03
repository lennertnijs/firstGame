package com.mygdx.game.game_object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.util.Vec2;
import com.mygdx.game.game_object.renderer.Direction;
import com.mygdx.game.game_object.renderer.Renderer;
import com.mygdx.game.inventory.item_visitor.ItemVisitor;

import java.util.Objects;

/**
 * A mutable generic game object. Can be extended.
 * A generic game object contains:
 * - a Transform (for anything position related)
 * - a Renderer (for anything render related)
 * - a map
 */
public class GameObject {

    protected final Transform transform;
    protected final Renderer renderer;
    protected String map;

    public GameObject(Transform transform, Renderer renderer, String map){
        this.transform = Objects.requireNonNull(transform);
        this.renderer = Objects.requireNonNull(renderer);
        this.map = Objects.requireNonNull(map);
    }

    /**
     * Fetches and returns the position, after adding a possible offset.
     */
    public final Vec2 getPosition(){
        return transform.getPosition().add(renderer.getOffSet());
    }

    /**
     * Updates the position of the object to the given position.
     */
    public final void setPosition(Vec2 position){
        transform.setPosition(position);
    }

    /**
     * Fetches and returns the texture (region).
     */
    public final TextureRegion getTexture(){
        return renderer.getTexture();
    }

    /**
     * Fetches and returns the width of the object's texture.
     */
    public final int getWidth(){
        return renderer.getWidth();
    }

    /**
     * Fetches and returns the height of the object's texture.
     */
    public final int getHeight(){
        return renderer.getHeight();
    }

    /**
     * Fetches and returns the direction of the game object, if applicable.
     */
    public final Direction getDirection(){
        return renderer.getDirection();
    }

    /**
     * Updates the direction of the object to the given direction.
     */
    public final void setDirection(Direction direction){
        renderer.setDirection(direction);
    }

    /**
     * Fetches and returns the activity of the game object, if applicable.
     */
    public final String getActivity(){
        return renderer.getActivity();
    }

    /**
     * Updates the activity of the object to the given activity.
     */
    public final void setActivity(String activity){
        renderer.setActivity(activity);
    }

    /**
     * Fetches and returns the object's map.
     */
    public final String getMap(){
        return map;
    }

    /**
     * Updates the map of the object to the given map.
     */
    public final void setMap(String map){
        this.map = Objects.requireNonNull(map);
    }


    public void accept(ItemVisitor visitor){

    }

    @Override
    public String toString(){
        return String.format("GameObject[transform=%s, renderer=%s, map=%s]", transform, renderer, map);
    }
}
