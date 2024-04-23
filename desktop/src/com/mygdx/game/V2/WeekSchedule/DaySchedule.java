package com.mygdx.game.V2.WeekSchedule;

import com.mygdx.game.V2.Util.Time;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class DaySchedule {

    private final List<Activity> activities;

    private DaySchedule(List<Activity> activities){
        this.activities = activities;
    }

    public static DaySchedule create(List<Activity> activities){
        Objects.requireNonNull(activities, "Cannot create a DaySchedule with null.");
        if(activities.contains(null))
            throw new NullPointerException("Cannot create a DaySchedule with a null Activity.");
        return new DaySchedule(activities);
    }

    public List<Activity> getActivities(){
        return activities;
    }

    public boolean isLastActivity(Activity activity){
        Objects.requireNonNull(activity, "Cannot check whether null is the last Activity.");
        int length = activities.size();
        return activities.get(length - 1).equals(activity);
    }


    public Activity getNextActivity(Activity activity){
        Objects.requireNonNull(activity, "Cannot get the next Activity of null.");
        for(int i = 0; i < activities.size() - 1; i++){
            if(activities.get(i).equals(activity)){
                return activities.get(i + 1);
            }
        }
        throw new NoSuchElementException("The given Activity was not found, or was the last Activity of the DaySchedule.");
    }


    public Activity getFirstActivityAfter(Time time){
        Objects.requireNonNull(time, "Cannot get the next Activity after null.");
        for(Activity activity : activities){
            if(activity.time().after(time)){
                return activity;
            }
        }
        throw new NoSuchElementException("No Activity was found after the given Time.");
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
