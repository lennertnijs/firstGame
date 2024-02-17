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

    protected Position getPosition(){
        return this.position;
    }

    protected float getHealth(){
        return this.health;
    }

    protected int getHardness(){
        return this.hardness;
    }

    protected void damage(float damage){
        if(damage < 0){
            throw new IllegalArgumentException("Cannot damage a tree with a negative amount. This would heal it.");
        }
        float newHealth = this.health - damage;
        this.health = newHealth < 0 ? 0 : newHealth;
    }

    protected boolean isBroken(){
        return this.health == 0;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Tree)){
            return false;
        }
        Tree tree = (Tree) o;
        return this.position.equals(tree.position) && this.health == tree.health && this.hardness == tree.hardness;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.position, this.health, this.hardness);
    }
}