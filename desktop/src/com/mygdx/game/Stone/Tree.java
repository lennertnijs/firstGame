package com.mygdx.game.Stone;

import com.mygdx.game.Entity.Position;

import java.util.Objects;

public class Tree {

    private final Position position;
    private float health;
    private final int hardness;
    private Tree(Position position, float health, int hardness){
        this.position = position;
        this.health = health;
        this.hardness = hardness;
    }

    public static Tree create(Position position, float health, int hardness){
        Objects.requireNonNull(position);
        if(health <= 0){
            throw new IllegalArgumentException("Tree health at creation must be strictly positive.");
        }
        if(hardness <= 0){
            throw new IllegalArgumentException("Tree hardness must be strictly positive.");
        }
        return new Tree(position, health, hardness);
    }
}
