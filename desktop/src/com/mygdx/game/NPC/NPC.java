package com.mygdx.game.NPC;

import com.mygdx.game.Entity.Entity;
import java.util.ArrayList;

public class NPC extends Entity {

    /**
     * MovementGraph movementNetwork
     * NPC description list (unlockable)
     * Dialogue tree
     */

    private final String name;
    private final WeekSchedule weekSchedule;
    private final ArrayList<Position2D> movementPath;
    private Activity activity;

    public NPC(Builder builder){
        super(builder.position, builder.spritePath);
        this.name = builder.name;
        this.weekSchedule = builder.weekSchedule;
        this.movementPath = builder.movementPath;
        this.activity = builder.activity;
    }

    public String getName(){
        return name;
    }

    public WeekSchedule getWeekSchedule(){
        return weekSchedule;
    }

    public ArrayList<Position2D> getMovementPath(){
        return this.movementPath;
    }

    public Activity getActivity(){
        return this.activity;
    }

    public static Builder builder(){
        return new NPC.Builder();
    }

    public static class Builder{

        // Entity fields
        private Position2D position;
        private String spritePath;

        // NPC fields
        private String name;
        private WeekSchedule weekSchedule;
        private ArrayList<Position2D> movementPath;
        private Activity activity;


        private Builder(){

        }

        public Builder position(Position2D position){
            this.position = position;
            return this;
        }

        public Builder spritePath(String spritePath){
            this.spritePath = spritePath;
            return this;
        }

        // NPC fields

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder weekSchedule(WeekSchedule weekSchedule){
            this.weekSchedule = weekSchedule;
            return this;
        }

        public Builder movementPath(ArrayList<Position2D> movementPath){
            this.movementPath = movementPath;
            return this;
        }

        public Builder addToMovementPath(Position2D position){
            this.movementPath.add(position);
            return this;
        }

        public Builder activity(Activity activity){
            this.activity = activity;
            return this;
        }

        public NPC build(){
            return new NPC(this);
        }
    }
}
