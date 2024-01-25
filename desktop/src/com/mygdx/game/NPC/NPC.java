package com.mygdx.game.NPC;

import com.mygdx.game.Entity.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class NPC extends Entity {

    /**
     * NPC description list (to unlock)
     * Dialogue tree
     */

    private final String name;
    private final WeekSchedule weekSchedule;
    private final ArrayList<Position2D> movementPath;
    private Activity activity;
    private final HashMap<Position2D, ArrayList<Position2D>> movementNetwork;

    public NPC(Builder builder){
        super(builder.position, builder.spritePath);
        this.name = builder.name;
        this.weekSchedule = builder.weekSchedule;
        this.movementPath = builder.movementPath;
        this.activity = builder.activity;
        this.movementNetwork = builder.movementNetwork;
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

    public HashMap<Position2D, ArrayList<Position2D>> getMovementNetwork(){
        return this.movementNetwork;
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
        private HashMap<Position2D, ArrayList<Position2D>> movementNetwork;


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

        public Builder movementNetwork(HashMap<Position2D, ArrayList<Position2D>> movementNetwork){
            this.movementNetwork = movementNetwork;
            return this;
        }

        public NPC build(){
            Objects.requireNonNull(position, "An npc's location must not be null");
            Objects.requireNonNull(spritePath, "An npc's sprite path must not be null");
            Objects.requireNonNull(name, "An npc's name must not be null");
            Objects.requireNonNull(weekSchedule, "An npc's week schedule must not be null");
            Objects.requireNonNull(movementPath, "An npc's movement path must not be null");
            for(Position2D movingToPoint : movementPath){
                Objects.requireNonNull(movingToPoint, "An npc's movement path must not contain null");
            }
            Objects.requireNonNull(activity, "An npc's activity must not be null");
            Objects.requireNonNull(movementNetwork, "An npc's movement network must not be null");
            for(Position2D point : movementNetwork.keySet()){
                Objects.requireNonNull(point, "An npc's movement network must not contain a null key");
                for(Position2D nextPoint : movementNetwork.get(point)){
                    Objects.requireNonNull(nextPoint,"An npc's movement network must not contain a null value");
                }
            }
            return new NPC(this);
        }
    }
}
