package com.mygdx.game.Breakables;

import com.mygdx.game.General.GameObject;
import com.mygdx.game.General.Sprite;

import java.util.Objects;

public class Breakable extends GameObject {

    private int health;
    private final int hardness;
    private final BreakableType type;

    public Breakable(Sprite sprite, int health, int hardness, BreakableType type){
        super(sprite);
        if(health <= 0){
            throw new IllegalArgumentException("Health is negative or 0.");
        }
        if(hardness <= 0){
            throw new IllegalArgumentException("Hardness is negative or 0.");
        }
        Objects.requireNonNull(type, "Type is null.");
        this.health = health;
        this.hardness = hardness;
        this.type = type;
    }

    public int getHealth(){
        return health;
    }

    public int getHardness(){
        return hardness;
    }

    public BreakableType getType(){
        return type;
    }

    public boolean isBroken(){
        return health == 0;
    }

    public void damage(int damage){
        if(damage < 0){
            throw new IllegalArgumentException("Damage is negative.");
        }
        this.health = Math.max(health - damage, 0);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Breakable)){
            return false;
        }
        if(!super.equals(other)){
            return false;
        }
        Breakable breakable = (Breakable) other;
        return health == breakable.health && hardness == breakable.hardness && type == breakable.type;
    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = result * 31 + health;
        result = result * 31 + hardness;
        result = result * 31 + type.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Breakable[%s, health=%d, hardness=%d, type=%s]", super.toString(), health, hardness, type);
    }
}
