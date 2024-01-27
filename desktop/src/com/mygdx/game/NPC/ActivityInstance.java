package com.mygdx.game.NPC;

import com.mygdx.game.Entity.Position;
import com.mygdx.game.Map.Map;

import java.util.Objects;

import static com.mygdx.game.Constants.MINUTES_PER_DAY;

public class ActivityInstance {

    private final Position position;
    private final int startTimeInMinutes;
    private final Activity activity;
    private final Map map;

    public ActivityInstance(Builder builder){
        this.position = builder.position;
        this.startTimeInMinutes = builder.startTimeInMinutes;
        this.activity = builder.activity;
        this.map = builder.map;
    }
    public Position getPosition(){
        return this.position;
    }

    public int getStartTimeInMinutes(){
        return this.startTimeInMinutes;
    }

    public Activity getActivity(){
        return this.activity;
    }

    public Map getMap(){
        return this.map;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof ActivityInstance)){
            return false;
        }
        ActivityInstance instance = (ActivityInstance) o;
        return position.equals(instance.position) &&
                startTimeInMinutes == instance.startTimeInMinutes &&
                activity.equals(instance.activity) &&
                map.equals(instance.map);
    }

    @Override
    public int hashCode(){
        return Objects.hash(position, startTimeInMinutes, activity, map);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Position position;
        private int startTimeInMinutes = -1;
        private Activity activity;
        private Map map;

        public Builder(){
        }

        public Builder position(Position position){
            this.position = position;
            return this;
        }

        public Builder startTimeInMinutes(int startTimeInMinutes){
            this.startTimeInMinutes = startTimeInMinutes;
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
            if(startTimeInMinutes < 0 || startTimeInMinutes >= MINUTES_PER_DAY){
                throw new IllegalArgumentException("The time of an activity instance must be valid");
            }
            Objects.requireNonNull(activity, "The activity of an activity instance must not be null");
            Objects.requireNonNull(map, "The map of an activity instance must not be null");
            return new ActivityInstance(this);
        }
    }
}
