package com.mygdx.game.WeekSchedule;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

import java.util.NoSuchElementException;

public interface IWeekSchedule {

    /**
     * Returns whether there is an {@link Activity} at the given {@link Time} in the {@link Schedule}
     * of the given {@link Day}.
     * @param day The {@link Day}. Cannot be null.
     * @param time The {@link Time}. Cannot be null.
     *
     * @return True if an {@link Activity} exists. False otherwise.
     * @throws NoSuchElementException If no mapping for the given {@link Day} exists.
     * @throws NullPointerException If the {@link Day} or {@link Time} is null.
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
     * @throws NullPointerException If the {@link Day} or {@link Time} is null.
     */
    Activity getActivity(Day day, Time time);
}
