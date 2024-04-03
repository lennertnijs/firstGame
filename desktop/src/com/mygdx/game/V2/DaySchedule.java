package com.mygdx.game.V2;

import java.util.List;
import java.util.NoSuchElementException;

public final class DaySchedule {

    private final List<Activity> activities;

    private DaySchedule(List<Activity> activities){
        this.activities = activities;
    }

    public static DaySchedule create(List<Activity> activities){
        Validator.notNull(activities);
        Validator.notContainsNull(activities);
        return new DaySchedule(activities);
    }

    public List<Activity> getActivities(){
        return activities;
    }

    public boolean isLastActivity(Activity activity){
        Validator.notNull(activity);
        int length = activities.size();
        return activities.get(length - 1).equals(activity);
    }

    public Activity getNextActivity(Time time){
        Validator.notNull(time);
        for(Activity activity : activities){
            if(activity.getTime().after(time)){
                return activity;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof DaySchedule)){
            return false;
        }
        DaySchedule schedule = (DaySchedule) other;
        return activities.equals(schedule.activities);
    }

    @Override
    public int hashCode(){
        return activities.hashCode();
    }

    @Override
    public String toString(){
        return String.format("DaySchedule[Activities=%s]", activities);
    }
}
