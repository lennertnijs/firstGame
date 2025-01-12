package game.game_object;

import game.util.Vec2;

import java.util.Objects;

/**
 * A Transform class encapsulating any game object data related to its position in the world.
 * A transform contains:
 * - a position (as a vector)
 */
public final class Transform {

    private Vec2 position;

    public Transform(Vec2 position){
        this.position = Objects.requireNonNull(position);
    }

    public Vec2 getPosition(){
        return position;
    }

    public void setPosition(Vec2 updatedPosition){
        this.position = Objects.requireNonNull(updatedPosition);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Transform transform))
            return false;
        return position.equals(transform.position);
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result * 31 + position.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Transform[position=%s]", position);
    
    }
}
