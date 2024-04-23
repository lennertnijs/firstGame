package com.mygdx.game.V2.WeekSchedule;

import com.mygdx.game.V2.Util.Day;
import com.mygdx.game.V2.Util.Time;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Represents a week schedule.
 * A week schedule maps days to schedules.
 * IMMUTABLE
 */
public final class WeekSchedule implements IWeekSchedule{

    /**
     * The schedules, mapped onto by their relevant day.
     */
    private final Map<Day, Schedule> schedules;

    /**
     * Creates a new immutable {@link WeekSchedule}.
     * @param schedules The map of {@link Day}s and their {@link Schedule}s. Cannot be null. Cannot contain a null.
     */
    public WeekSchedule(Map<Day, Schedule> schedules){
        Objects.requireNonNull(schedules, "Map is null.");
        if(schedules.containsKey(null))
            throw new NullPointerException("Map contains null key.");
        if(schedules.containsValue(null))
            throw new NullPointerException("Map contains null value.");
        this.schedules = new HashMap<>(schedules);
    }

    /**
     * {@inheritDoc}
     */
    public Map<Day, Schedule> schedules(){
        return new HashMap<>(schedules);
    }

    /**
     * {@inheritDoc}
     */
    public Schedule getSchedule(Day day){
        validateDay(day);
        return schedules.get(day);
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasActivity(Day day, Time time){
        validateDay(day);
        Objects.requireNonNull(time, "Time is null.");
        return schedules.get(day).hasActivityAt(time);
    }

    /**
     * {@inheritDoc}
     */
    public Activity getActivity(Day day, Time time){
        validateDay(day);
        Objects.requireNonNull(time, "Time is null.");
        return schedules.get(day).getActivityAt(time);
    }

    /**
     * Validates the given {@link Day}.
     * @param day The {@link Day}. Cannot be null.
     *
     * @throws NoSuchElementException If no mapping for the given {@link Day} exists.
     */
    private void validateDay(Day day){
        Objects.requireNonNull(day, "Day is null.");
        if(!schedules.containsKey(day))
            throw new NoSuchElementException("No Schedule found for this Day.");
    }

    /**
     * Compares this {@link WeekSchedule} to the given object and returns true if they're equal. Returns false otherwise.
     * Two {@link WeekSchedule}s are the same if they have the same (key, value) pairs.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof WeekSchedule))
            return false;
        WeekSchedule schedule = (WeekSchedule) other;
        return schedules.equals(schedule.schedules);
    }

    /**
     * @return The hash code of this {@link WeekSchedule}.
     */
    @Override
    public int hashCode(){
        return schedules.hashCode();
    }

    /**
     * @return THe string representation of this {@link WeekSchedule}.
     */
    @Override
    public String toString(){
        return String.format("WeekSchedule[Schedules=%s]", schedules);
    }
}