package com.mygdx.game.NPC;

import com.mygdx.game.Map.Map;

public class ActivityInstance {

    final Position2D location;
    final int timeInMinutes;
    final Activity activity;
    final Map map;

    /**
     * The {@code Activity} constructor
     * Builds a valid {@code Activity} with a {@code Builder}
     */
    public ActivityInstance(Builder builder){
        this.location = builder.location;
        this.timeInMinutes = builder.timeInMinutes;
        this.activity = builder.activity;
        this.map = builder.map;
    }
    public Position2D getLocation(){
        return this.location;
    }

    public int getTimeInMinutes(){
        return this.timeInMinutes;
    }

    public Activity getAction(){
        return this.activity;
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
        public Activity activity;
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

        public Builder action(Activity activity){
            this.activity = activity;
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
        public ActivityInstance build(){
            if(map == null || activity == null){
                throw new IllegalArgumentException("No screen or animation can be null.");
            }
            return new ActivityInstance(this);
        }
    }
}
