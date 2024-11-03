package com.mygdx.game.npc.week_schedule;

import java.util.*;
import java.util.stream.Collectors;

public final class Schedule {

    private final List<Activity> activities;

    /**
     * @throws IllegalArgumentException If two {@link Activity} instances happen at the exact same {@link Time}.
     */
    public Schedule(List<Activity> activities){
        Objects.requireNonNull(activities, "List is null.");
        if(activities.contains(null)) {
            throw new NullPointerException("List contains null.");
        }
        if(hasTwoActivitiesAtSameTime(activities)){
            throw new IllegalArgumentException("Two activities happen at the exact same Time.");
        }
        this.activities = activities.stream().sorted(Comparator.comparing(Activity::time)).collect(Collectors.toList());
    }

    private boolean hasTwoActivitiesAtSameTime(List<Activity> activities){
        return activities.stream().map(Activity::time).distinct().count() < activities.size();
    }

    public Activity getActivityAt(Time time){
        Objects.requireNonNull(time, "Time is null.");
        return activities.stream().filter(a -> a.time().compareTo(time) == 0).findFirst().orElse(null);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Schedule schedule))
            return false;
        return activities.equals(schedule.activities);
    }

    @Override
    public int hashCode(){
        return activities.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Schedule[Activities=%s]", activities);
    }
}
