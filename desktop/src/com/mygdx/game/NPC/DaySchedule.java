package com.mygdx.game.NPC;

import com.mygdx.game.Clock.Day;

import java.util.ArrayList;
import java.util.Objects;

public class DaySchedule {

    private final Day day;
    private final ArrayList<ActivityInstance> activities;

    public DaySchedule(Builder builder){
        this.day = builder.day;
        this.activities = builder.activities;
    }

    public Day getDay(){
        return this.day;
    }

    public ArrayList<ActivityInstance> getActivities(){
        return this.activities;
    }

    public ActivityInstance next(ActivityInstance activityInstance){
        Objects.requireNonNull(activityInstance, "Cannot find the next activity instance of null");
        int index = findIndexOfActivityInstance(activityInstance);
        int size = activities.size();
        if(index == size-1){
            return null;
        }
        return activities.get(index+1);
    }

    private int findIndexOfActivityInstance(ActivityInstance activityInstance){
        int index = activities.indexOf(activityInstance);
        if(index == -1){
            throw new IllegalArgumentException("Could not find the activity instance");
        }
        return index;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Day day;
        private ArrayList<ActivityInstance> activities = new ArrayList<>();

        public Builder(){

        }

        public Builder day(Day day){
            this.day = day;
            return this;
        }

        public Builder activities(ArrayList<ActivityInstance> activities){
            this.activities = activities;
            return this;
        }

        public Builder addActivity(ActivityInstance activity){
            this.activities.add(activity);
            return this;
        }

        public DaySchedule build(){
            Objects.requireNonNull(day, "The day of the day schedule must not be null");
            Objects.requireNonNull(activities, "The list of activities of the day schedule must not be null");
            for(ActivityInstance activityInstance : activities){
                Objects.requireNonNull(activityInstance, "The activity instance of the day schedule must not be null");
            }
            return new DaySchedule(this);
        }

    }

}
