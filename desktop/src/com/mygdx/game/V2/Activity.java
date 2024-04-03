package com.mygdx.game.V2;

public final class Activity {

    private final Position position;
    private final Time time;
    private final ActivityType type;
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

    public ActivityType getType(){
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

        private int x = -1;
        private int y = -1;
        private Position position;
        private int hours = -1;
        private int minutes = -1;
        private Time time;
        private ActivityType type;
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

        public Builder type(ActivityType type){
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
            Validator.notNull(type);
            Validator.notNull(mapName);
            return new Activity(this);
        }
    }

}
