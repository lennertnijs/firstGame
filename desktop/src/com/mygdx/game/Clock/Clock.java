package com.mygdx.game.Clock;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

import java.util.Objects;

public final class Clock implements IClock{

    private final static int MILLIS_PER_UPDATE = 1000;
    private final static int MINUTES_PER_UPDATE = 1;
    private final CalendarClock calendarClock;
    private final TimeProvider timeProvider;
    private float millisSinceUpdate;
    private boolean active;
    private double lastDelta;

    public Clock(CalendarClock calendarClock, TimeProvider timeProvider){
        this.calendarClock = Objects.requireNonNull(calendarClock, "Calendar clock is null.").copy();
        this.timeProvider = Objects.requireNonNull(timeProvider, "Time provider is null.").copy();
        this.millisSinceUpdate = 0;
        this.active = true;
    }

    public Day getDay(){
        return calendarClock.getDay();
    }

    public Time getTime(){
        return calendarClock.getTime();
    }

    public boolean isActive(){
        return active;
    }

    public void start(){
        active = true;
        timeProvider.reset();
    }

    public void pause(){
        active = false;
    }

    public double update(){
        if(!active) return 0;

        this.lastDelta = timeProvider.update();
        millisSinceUpdate += lastDelta;
        if(millisSinceUpdate >= MILLIS_PER_UPDATE){
            calendarClock.increaseTime(MINUTES_PER_UPDATE);
            millisSinceUpdate -= MILLIS_PER_UPDATE;
        }
        return lastDelta;
    }

    public double getLastDelta(){
        return lastDelta;
    }

    public void reset(){
        timeProvider.reset();
        millisSinceUpdate = 0;
    }
}
