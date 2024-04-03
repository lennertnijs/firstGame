package com.mygdx.game.V2;

public final class Activity {

    private final Position position;
    private final Time time;
    private final NPCActivityType activityType;
    private final String mapName;

    private Activity(Builder builder){
        this.position = builder.position;
        this.time = builder.time;
        this.activityType = builder.activityType;
        this.mapName = builder.mapName;
    }

    public Position getPosition(){
        return position;
    }

    public Time getTime(){
        return time;
    }

    public NPCActivityType getActivityType(){
        return activityType;
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
                activityType.equals(activity.activityType) &&
                mapName.equals(activity.mapName);
    }

    @Override
    public int hashCode(){
        int result = position.hashCode();
        result = result * 31 + time.hashCode();
        result = result * 31 + activityType.hashCode();
        result = result * 31 + mapName.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Activity[Position = %s, Time = %s, ActivityType = %s, MapName = %s]",
                position, time, activityType, mapName);
    }


    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder{

        public int x = -1;
        public int y = -1;
        public Position position;

        public int hours;
        public int minutes;

        public Time time;
        public NPCActivityType activityType;
        public String mapName;


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

        public Builder NPCActivityType(NPCActivityType activityType){
            this.activityType = activityType;
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
            Validator.notNull(activityType);
            Validator.notNull(mapName);
            return new Activity(this);
        }
    }

}
