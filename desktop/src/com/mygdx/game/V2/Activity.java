package com.mygdx.game.V2;

import java.util.Objects;

public final class Activity {

    private final Position position;
    private final Time time;
    private final NPCActivityType type;
    private final String mapName;

    private Activity(Builder builder){
        this.position = builder.position;
        this.time = builder.time;
        this.type = builder.type;
        this.mapName = builder.mapName;
    }

    public Position getPosition(){
        return position;
    }

    public Time getTime(){
        return time;
    }

    public NPCActivityType getType(){
        return type;
    }

    public String getMapName(){
        return mapName;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Activity)){
            return false;
        }
        Activity activity = (Activity) other;
        return position.equals(activity.position) &&
                time.equals(activity.time) &&
                type.equals(activity.type) &&
                mapName.equals(activity.mapName);
    }

    @Override
    public int hashCode(){
        int result = position.hashCode();
        result = result * 31 + time.hashCode();
        result = result * 31 + type.hashCode();
        result = result * 31 + mapName.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Activity[%s, %s, Type=%s, MapName=%s]",
                position, time, type, mapName);
    }


    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder{

        // Position related fields
        private int x = -1;
        private int y = -1;
        private Position position;

        // Time related fields
        private int hours = -1;
        private int minutes = -1;
        private Time time;

        // The others
        private NPCActivityType type;
        private String mapName;

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

        public Builder position(Position position){
            this.position = position;
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

        public Builder type(NPCActivityType type){
            this.type = type;
            return this;
        }

        public Builder mapName(String mapName){
            this.mapName = mapName;
            return this;
        }

        public Activity build(){
            if(position == null){
                position = Position.create(x, y);
            }
            if(time == null){
                time = Time.create(hours, minutes);
            }
            Objects.requireNonNull(type, "Cannot create an Activity with a null ActivityType.");
            Objects.requireNonNull(mapName, "Cannot create an Activity with a null map name.");
            return new Activity(this);
        }
    }

}
