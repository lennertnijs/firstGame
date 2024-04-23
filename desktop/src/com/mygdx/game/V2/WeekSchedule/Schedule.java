package com.mygdx.game.V2.WeekSchedule;

import com.mygdx.game.V2.Util.Time;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a schedule. A schedule is a sorted collection of activities.\
 * IMMUTABLE
 */
public final class Schedule {

    /**
     * The sorted list of activities.
     */
    private final List<Activity> activities;

    /**
     * Creates a new immutable {@link Schedule}.
     * @param activities The list of {@link Activity} objects. Cannot be null. Cannot contain null.
     *
     * @throws IllegalArgumentException If two {@link Activity} instances happen at the exact same {@link Time}.
     */
    public Schedule(List<Activity> activities){
        Objects.requireNonNull(activities, "List is null.");
        if(activities.contains(null))
            throw new NullPointerException("List contains null.");
        checkForActivitiesAtSameTime(activities);
        this.activities = new ArrayList<>(activities).stream()
                .sorted(Comparator.comparing(Activity::time))
                .collect(Collectors.toList());
    }

    /**
     * Checks that the list of activities does not contain 2 activities happening at the same time.
     * @param activities The list of {@link Activity} objects. Cannot be null. Cannot contain null.
     *
     * @throws IllegalArgumentException If two {@link Activity} instances happen at the exact same {@link Time}.
     */
    private void checkForActivitiesAtSameTime(List<Activity> activities){
        Set<Time> uniqueTimes = new HashSet<>();
        for(Activity activity : activities)
            if(!uniqueTimes.add(activity.time()))
                throw new IllegalArgumentException("Two activities happen at the exact same Time.");
    }

    /**
     * @return The sorted list of {@link Activity} objects.
     */
    public List<Activity> activities(){
        return new ArrayList<>(activities);
    }

    /**
     * Checks whether this {@link Schedule} contains any {@link Activity} objects that happen
     * at or after the given {@link Time}.
     * @param time The {@link Time}. Cannot be null.
     *
     * @return True if any {@link Activity} happens at or after the {@link Time}. False otherwise.
     */
    public boolean hasActivitiesAfter(Time time){
        Objects.requireNonNull(time, "Time is null.");
        return activities.stream().anyMatch(activity -> activity.time().compareTo(time) >= 0);
    }

    /**
     * Fetches and returns the first {@link Activity} that will happen at or after the given {@link Time}.
     * @param time The {@link Time}. Cannot be null.
     *
     * @return The {@link Activity}.
     * @throws NoSuchElementException If no {@link Activity} at or after was found.
     */
    public Activity getActivityAfter(Time time){
        Objects.requireNonNull(time, "Time is null.");
        return activities.stream()
                .filter(activity -> activity.time().compareTo(time) >= 0)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No Activity happens at or after this Time."));
    }

    /**
     * Compares this {@link Schedule} to the given object and returns true if they're equal. Returns false otherwise.
     * Two {@link Schedule}s are equal if they hold the same {@link Activity} objects.
     * Note that the order they were in when creating a {@link Schedule} does not matter,
     * as they get sorted by their {@link Time}.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Schedule))
            return false;
        Schedule schedule = (Schedule) other;
        return activities.equals(schedule.activities);
    }

    /**
     * @return The hash code of this {@link Schedule}.
     */
    @Override
    public int hashCode(){
        return activities.hashCode();
    }

    /**
     * @return The string representation of this {@link Schedule}.
     */
    @Override
    public String toString(){
        return String.format("Schedule[Activities=%s]", activities);
    }
}
