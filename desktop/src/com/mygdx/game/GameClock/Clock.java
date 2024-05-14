package com.mygdx.game.GameClock;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

import java.util.Objects;

public final class Clock {

    private Day day;
    private Time time;

    public Clock(Day day, Time time){
        this.day = Objects.requireNonNull(day, "Day is null.");
        this.time = Objects.requireNonNull(time, "Time is null.");
    }

    public void incrementTime(int increase){
        if(increase <= 0){
            throw new IllegalArgumentException("Increase is negative or 0.");
        }
        int totalMinutes = time.asMinutes() + increase;
        while(totalMinutes >= Time.MINUTES_PER_DAY){
            day = day.next();
            totalMinutes -= Time.MINUTES_PER_DAY;
        }
        this.time = new Time(totalMinutes);
    }
}
