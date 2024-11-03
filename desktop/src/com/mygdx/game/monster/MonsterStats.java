package com.mygdx.game.monster;

public final class MonsterStats {

    public static int MIN_SPEED = 10;
    private int health;
    private int offense;
    private int defense;
    private int speed;
    private final int aggressionRange;

    public MonsterStats(int health, int offense, int defense, int speed, int aggressionRange){
        this.health = health;
        this.offense = offense;
        this.defense = defense;
        this.speed = speed;
        this.aggressionRange = aggressionRange;
    }

    public int getSpeed(){
        return speed;
    }

    public int aggressionRange(){
        return aggressionRange;
    }
}
