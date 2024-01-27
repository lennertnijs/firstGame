package com.mygdx.game.NPC;

import com.mygdx.game.Entity.Position;
import com.mygdx.game.Map.Map;

import java.util.Objects;

import static com.mygdx.game.Constants.MINUTES_PER_DAY;

public class ActivityInstance {

    final Position position;
    final int timeInMinutes;
    final Activity activity;
    final Map map;

    public ActivityInstance(Builder builder){
        this.position = builder.position;
        this.timeInMinutes = builder.timeInMinutes;
        this.activity = builder.activity;
        this.map = builder.map;
    }
    public Position getPosition(){
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

        private Position position;
        private int timeInMinutes;
        private Activity activity;
        private Map map;

        public Builder(){
        }

        public Builder position(Position position){
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
            if(timeInMinutes < 0 || timeInMinutes >= MINUTES_PER_DAY){
                throw new IllegalArgumentException("The time (in minutes) is invalid");
            }
            return new ActivityInstance(this);
        }
    }
}
