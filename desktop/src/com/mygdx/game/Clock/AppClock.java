package com.mygdx.game.Clock;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

public final class AppClock {

    private final static int MILLIS_PER_UPDATE = 1000;
    private final CalendarClock calendarClock;
    private final TimeProvider timeProvider;
    private float millisSinceUpdate;
    private boolean active;

    public AppClock(CalendarClock calendarClock, TimeProvider timeProvider){
        this.calendarClock = calendarClock;
        this.timeProvider = timeProvider;
        this.millisSinceUpdate = 0;
        this.active = false;
    }

    public void update(){
        millisSinceUpdate += timeProvider.update();
        if(millisSinceUpdate > MILLIS_PER_UPDATE){
            calendarClock.increaseTime(1);
            millisSinceUpdate -= MILLIS_PER_UPDATE;
        }
    }

    public Day getDay(){
        return calendarClock.getDay();
    }

    public Time getTime(){
        return calendarClock.getTime();
    }

    public void reset(){
        timeProvider.reset();
    }
}
