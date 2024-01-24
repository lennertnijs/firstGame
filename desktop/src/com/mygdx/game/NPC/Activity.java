package com.mygdx.game.NPC;

import com.mygdx.game.Map.Map;

public class Activity {

    final Position2D location;
    final int timeInMinutes;
    final Action action;
    final Map map;

    /**
     * The {@code Activity} constructor
     * Builds a valid {@code Activity} with a {@code Builder}
     */
    public Activity(Builder builder){
        this.location = builder.location;
        this.timeInMinutes = builder.timeInMinutes;
        this.action = builder.action;
        this.map = builder.map;
    }
    public Position2D getLocation(){
        return this.location;
    }

    public int getTimeInMinutes(){
        return this.timeInMinutes;
    }

    public Action getAction(){
        return this.action;
    }

    public Map getMap(){
        return this.map;
    }

    /**
     * @return A static {@code Builder} for an {@code Activity}
     */
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Position2D location;
        public int timeInMinutes;
        public Action action;
        public Map map;

        public Builder(){

        }

        public Builder location(Position2D location){
            this.location = location;
            return this;
        }

        public Builder timeInMinutes(int timeInMinutes){
            this.timeInMinutes = timeInMinutes;
            return this;
        }

        public Builder action(Action action){
            this.action = action;
            return this;
        }

        public Builder map(Map map){
            this.map = map;
            return this;
        }

        /**
         * Builds a valid {@code Activity} instance.
         *
         * @return A valid {@code Activity} instance.
         */
        public Activity build(){
            if(map == null || action == null){
                throw new IllegalArgumentException("No screen or animation can be null.");
            }
            return new Activity(this);
        }
    }
}
