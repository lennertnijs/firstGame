package com.mygdx.game.GameClock;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

public final class GameClock {

    public Time time;
    public Day day;

    private TimeInput timeInput;
    private float millisSinceUpdate;

    public GameClock(Time t, Day d, TimeInput input){
        this.time = t;
        this.day = d;
        this.timeInput = input;
        this.millisSinceUpdate = 0;
    }

    public void update(){
        millisSinceUpdate += timeInput.update();
        if(millisSinceUpdate > 1000){
            handleClockUpdate();
            millisSinceUpdate -= 1000;
        }
    }

    public void handleClockUpdate(){
        if(time.minutes() + 1 < 60) {
            time = new Time(time.hours(), time.minutes() + 1);
            return;
        }
        if(time.hours() + 1 < 24){
            time = new Time(time.hours() + 1, 0);
            return;
        }
        this.day = Day.TUESDAY;
    }
}
