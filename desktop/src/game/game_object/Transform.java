package game.game_object;

import game.util.Vec2;

import java.util.Objects;

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
        return 17 * 31 + position.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Transform[position=%s]", position);
    }
}
