package com.mygdx.game.NPC;

import com.mygdx.game.Map.Map;

import java.util.Objects;

public class ActivityInstance {

    final Position2D position;
    final int timeInMinutes;
    final Activity activity;
    final Map map;

    public ActivityInstance(Builder builder){
        this.position = builder.position;
        this.timeInMinutes = builder.timeInMinutes;
        this.activity = builder.activity;
        this.map = builder.map;
    }
    public Position2D getPosition(){
        return this.position;
    }

    public int getTimeInMinutes(){
        return this.timeInMinutes;
    }

    public Activity getActivity(){
        return this.activity;
    }

    public Map getMap(){
        return this.map;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Position2D position;
        private int timeInMinutes;
        private Activity activity;
        private Map map;

        public Builder(){
        }

        public Builder position(Position2D position){
            this.position = position;
            return this;
        }

        public Builder timeInMinutes(int timeInMinutes){
            this.timeInMinutes = timeInMinutes;
            return this;
        }

        public Builder activity(Activity activity){
            this.activity = activity;
            return this;
        }

        public Builder map(Map map){
            this.map = map;
            return this;
        }

        public ActivityInstance build(){
            Objects.requireNonNull(position, "The position of an activity instance must not be null");
            Objects.requireNonNull(activity, "The activity of an activity instance must not be null");
            Objects.requireNonNull(map, "The map of an activity instance must not be null");
            return new ActivityInstance(this);
        }
    }
}
