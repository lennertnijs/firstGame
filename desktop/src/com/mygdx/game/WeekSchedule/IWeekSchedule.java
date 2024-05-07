package com.mygdx.game.WeekSchedule;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Represents a week schedule.
 * IMMUTABLE
 */
public interface IWeekSchedule {

    /**
     * @return The map of {@link Day}s to {@link Schedule}s.
     */
    Map<Day, Schedule> schedules();

    /**
     * Fetches and returns the {@link Schedule} for the given {@link Day}, if it exists.
     *
     * @param day The {@link Day}. Cannot be null.
     *
     * @return The {@link Schedule}.
     * @throws NoSuchElementException If no mapping for the given {@link Day} exists.
     */
    Schedule getDaySchedule(Day day);

    /**
     * Returns whether there is an {@link Activity} at the given {@link Time} in the {@link Schedule}
     * of the given {@link Day}.
     * @param day The {@link Day}. Cannot be null.
     * @param time The {@link Time}. Cannot be null.
     *
     * @return True if an {@link Activity} exists. False otherwise.
     * @throws NoSuchElementException If no mapping for the given {@link Day} exists.
     */
    boolean hasActivity(Day day, Time time);

    /**
     * Returns the {@link Activity} at the given {@link Time} in the {@link Schedule} of the given {@link Day}.
     * @param day The {@link Day}. Cannot be null.
     * @param time The {@link Time}. Cannot be null.
     *
     * @return The {@link Activity}.
     * @throws NoSuchElementException If no mapping for the given {@link Day} exists.
     * @throws NoSuchElementException If no {@link Activity} was found at the given {@link Time} in the
     *         {@link Day}'s {@link Schedule}.
     */
    Activity getActivity(Day day, Time time);
}
