package com.mygdx.game.clock;

import com.mygdx.game.npc.week_schedule.Day;
import com.mygdx.game.npc.week_schedule.Time;

import java.util.Objects;

public final class CalendarClock {

    private Day day;
    private Time time;

    public CalendarClock(Day day, Time time){
        this.day = Objects.requireNonNull(day, "Day is null.");
        this.time = Objects.requireNonNull(time, "Time is null.");
    }

    public Day getDay(){
        return day;
    }

    public Time getTime(){
        return time;
    }

    public void increaseTime(int increase){
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

    public CalendarClock copy(){
        return new CalendarClock(day, time);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof CalendarClock))
            return false;
        CalendarClock clock = (CalendarClock) other;
        return day == clock.day && time.equals(clock.time);
    }

    @Override
    public int hashCode(){
        int result = day.hashCode();
        result = result * 31 + time.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("CalendarClock[day=%s, time=%s]", day, time);
    }
}
