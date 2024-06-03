package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;

import java.util.Objects;

public final class MonsterData {

    private final Point playerPos;
    private final Point monsterPos;
    private final int movementSpeed;
    private final double delta;

    public MonsterData(Point playerPos, Point monsterPos, int movementSpeed, double delta){
        this.playerPos = Objects.requireNonNull(playerPos, "Player position is null.");
        this.monsterPos = Objects.requireNonNull(monsterPos, "Monster position is null.");
        if(movementSpeed <= 0){
            throw new IllegalArgumentException("Movement speed is negative or 0.");
        }
        this.movementSpeed = movementSpeed;
        if(delta < 0){
            throw new IllegalArgumentException("Delta is negative.");
        }
        this.delta = delta;
    }

    public Point playerPosition(){
        return playerPos;
    }

    public Point monsterPosition(){
        return monsterPos;
    }

    public int movementSpeed(){
        return movementSpeed;
    }

    public double delta(){
        return delta;
    }
}
