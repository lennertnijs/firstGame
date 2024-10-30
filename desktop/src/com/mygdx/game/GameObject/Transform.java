package com.mygdx.game.GameObject;

import com.mygdx.game.Util.Vec2;

import java.util.Objects;

public final class Transform {

    private Vec2 position;
    float rotation;

    public Transform(Vec2 position, float rotation){
        this.position = Objects.requireNonNull(position);
        this.rotation = rotation;
    }

    public Vec2 getPosition(){
        return position;
    }

    public void setPosition(Vec2 updatedPosition){
        this.position = Objects.requireNonNull(updatedPosition);
    }

    public float getRotation(){
        return rotation;
    }

    public void setRotation(float updatedRotation){
        this.rotation = updatedRotation;
    }
}
