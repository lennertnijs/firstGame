package com.mygdx.game.V2.WeekSchedule;

import com.mygdx.game.V2.Util.ActivityType;
import com.mygdx.game.V2.Util.Point;
import com.mygdx.game.V2.Util.Time;

import java.util.Objects;

public final class Activity {

    private final Location location;
    private final Time time;
    private final ActivityType type;

    private Activity(Builder builder){
        this.location = builder.location;
        this.time = builder.time;
        this.type = builder.type;
    }

    public Location getLocation(){
        return location;
    }

    public Time getTime(){
        return time;
    }

    public ActivityType getType(){
        return type;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Activity)){
            return false;
        }
        Activity activity = (Activity) other;
        return location.equals(activity.location) &&
                time.equals(activity.time) &&
                type.equals(activity.type);
    }

    @Override
    public int hashCode(){
        int result = location.hashCode();
        result = result * 31 + time.hashCode();
        result = result * 31 + type.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Activity[%s, %s, Type=%s]",
                location, time, type);
    }


    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder{

        // Position related fields
        private int x = -1;
        private int y = -1;
        private Point point;
        private String mapName;
        private Location location;

        // Time related fields
        private int hours = -1;
        private int minutes = -1;
        private Time time;

        // The others
        private ActivityType type;

        private Builder(){

        }

        public Builder x(int x){
            this.x = x;
            return this;
        }

        public Builder y(int y){
            this.y = y;
            return this;
        }

        public Builder position(Point point){
            this.point = point;
            return this;
        }

        public Builder location(Location location){
            this.location = location;
            return this;
        }

        public Builder time(Time time){
            this.time = time;
            return this;
        }

        public Builder hours(int hours){
            this.hours = hours;
            return this;
        }

        public Builder minutes(int minutes){
            this.minutes = minutes;
            return this;
        }

        public Builder type(ActivityType type){
            this.type = type;
            return this;
        }

        public Builder mapName(String mapName){
            this.mapName = mapName;
            return this;
        }

        public Activity build(){
            Objects.requireNonNull(mapName, "Cannot create an Activity with a null map name.");
            if (location == null) {
                if(point == null){
                    point = new Point(x, y);
                }
                location = Location.create(mapName, point);
            }
            if(time == null){
                time = new Time(hours, minutes);
            }
            Objects.requireNonNull(type, "Cannot create an Activity with a null ActivityType.");
            return new Activity(this);
        }
    }

}
