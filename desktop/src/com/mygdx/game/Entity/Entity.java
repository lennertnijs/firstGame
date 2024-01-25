package com.mygdx.game.Entity;

import com.mygdx.game.NPC.Position2D;

public class Entity {

    private Position2D position;
    final private String spritePath;

    public Entity(Position2D position, String spritePath){
        this.position = position;
        this.spritePath = spritePath;
    }

    public Position2D getPosition() {
        return position;
    }

    public String getSpritePath() {
        return spritePath;
    }
}
