package com.mygdx.game.NPC;


import com.mygdx.game.Entity.Entity;

public class NPC extends Entity {

    /**
     * string name
     * Week schedule -> maps each day to dayschedule
     * LinkedList<Node> path waarbij nodes Position2D
     * Action action (idle, mining, ...)
     * MovementGraph movementNetwork
     * NPC description list (unlockable)
     * Dialogue tree
     *
     *
     */

    /**
     *      * position2D position
     *      * spriteName
     *
     */


    private final String name;
    private WeekSchedule weekSchedule;
    private Position2D goalPosition;

    private String png;

    public NPC(Builder builder){
        super(builder.position, "string");
        this.name = builder.name;
        this.weekSchedule = builder.weekSchedule;
        this.png = builder.png;
        this.goalPosition = builder.goalPosition;
        }

    public void update(){
        this.move();
    }

    public WeekSchedule getWeekSchedule(){
        return weekSchedule;
    }

    public String getPng(){
        return png;
    }

    public Position2D getGoalPosition(){
        return this.goalPosition;
    }

    public String getName(){
        return name;
    }

    public void setPosition(){

    }

    public static Builder builder(){
        return new NPC.Builder();
    }

    public void move(){
    }

    public static class Builder{

        private Position2D position;
        private String name;
        private Position2D goalPosition;
        private WeekSchedule weekSchedule;
        private String png;

        private Builder(){

        }

        public Builder position(Position2D position){
            this.position = position;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder weekSchedule(WeekSchedule weekSchedule){
            this.weekSchedule = weekSchedule;
            return this;
        }

        public Builder png(String png){
            this.png = png;
            return this;
        }

        public Builder goalPosition(Position2D goalPosition){
            this.goalPosition = goalPosition;
            return this;
        }

        public NPC build(){
            return new NPC(this);
        }
    }
}
