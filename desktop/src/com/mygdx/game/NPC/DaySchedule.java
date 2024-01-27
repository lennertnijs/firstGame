package com.mygdx.game.NPC;

import java.util.ArrayList;
import java.util.Objects;

import static com.mygdx.game.Constants.MINUTES_PER_DAY;

public class DaySchedule {
    private final ArrayList<ActivityInstance> activities;

    public DaySchedule(Builder builder){
        this.activities = builder.activities;
    }

    public ArrayList<ActivityInstance> getActivities(){
        return this.activities;
    }

    public ActivityInstance nextActivityAfterTime(int time){
        if(time < 0 || time >= MINUTES_PER_DAY){
            throw new IllegalArgumentException("The time to find the next activity for must not be invalid");
        }
        for(ActivityInstance activity: activities){
            if(activity.getStartTimeInMinutes() >= time){
                return activity;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof DaySchedule)){
            return false;
        }
        DaySchedule daySchedule = (DaySchedule) o;
        return activities.equals(daySchedule.activities);
    }
    @Override
    public int hashCode(){
        return Objects.hash(activities);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private ArrayList<ActivityInstance> activities = null;

        public Builder(){
        }

        public Builder activities(ArrayList<ActivityInstance> activities){
            this.activities = activities;
            return this;
        }

        public DaySchedule build(){
            Objects.requireNonNull(activities, "The list of activity instances of the day schedule must not be null");
            for(ActivityInstance activityInstance : activities){
                Objects.requireNonNull(activityInstance, "The list of activity instances of the day schedule must not contain null");
            }
            return new DaySchedule(this);
        }

    }

}
