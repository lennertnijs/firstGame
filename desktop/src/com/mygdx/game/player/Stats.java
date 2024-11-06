package com.mygdx.game.player;

public final class Stats {

    public static int MIN_SPEED = 1;
    private int health;
    private int offense;
    private int defense;
    private int speed;

    private Stats(Builder builder){
        this.health = builder.health;
        this.offense = builder.offense;
        this.defense = builder.defense;
        this.speed = builder.speed;
    }

    public int getHealth(){
        return health;
    }

    /**
     * @param health The new health. Can be 0 on death.
     */
    public void setHealth(int health){
        if(health < 0)
            throw new IllegalArgumentException("Cannot set an npc's health to a negative value.");
        this.health = health;

    }

    public int getOffense(){
        return offense;
    }

    public void setOffense(int offense){
        if(offense < 0)
            throw new IllegalArgumentException("Cannot set an npc's offense to a negative value.");
        this.offense = offense;
    }

    public int getDefense(){
        return defense;
    }

    public void setDefense(int defense){
        if(defense < 0)
            throw new IllegalArgumentException("Cannot set an npc's defense to a negative value.");
        this.defense = defense;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        if(speed < MIN_SPEED)
            throw new IllegalArgumentException("Cannot set an npc's speed to a value lower than MIN_NPC_SPEED.");
        this.speed = speed;
    }

    @Override
    public String toString(){
        return String.format("NPCStats[health=%d, offense=%d, defense=%d, speed=%d]", health, offense, defense, speed);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private int health;
        private int offense;
        private int defense;
        private int speed;

        private Builder(){
        }

        public Builder health(int health){
            this.health = health;
            return this;
        }

        public Builder offense(int offense){
            this.offense = offense;
            return this;
        }

        public Builder defense(int defense){
            this.defense = defense;
            return this;
        }

        public Builder speed(int speed){
            this.speed = speed;
            return this;
        }

        public Stats build(){
            if(health <= 0)
                throw new IllegalArgumentException("The health of an npc cannot be negative or 0.");
            if(offense < 0)
                throw new IllegalArgumentException("The offense of an npc cannot be negative.");
            if(defense < 0)
                throw new IllegalArgumentException("The defense of an npc cannot be negative");
            if(speed < MIN_SPEED){
                throw new IllegalArgumentException("The speed of an npc cannot be smaller than MIN_NPC_SPEED.");
            }
            return new Stats(this);
        }

    }
}
