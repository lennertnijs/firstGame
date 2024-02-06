package com.mygdx.game.Clock;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.Clock.ClockService;
import com.mygdx.game.Clock.Day;
import com.mygdx.game.Clock.Season;

public class ClockController {

    private final ClockService clockService;

    public ClockController(ClockService clockService){
        this.clockService = clockService;
    }

    public Day getDay(){
        return clockService.getClock().getDay();
    }

    public String getTime(){
        return clockService.getClock().getTimeInHHMM();
    }

    public Season getSeason(){
        return clockService.getClock().getSeason();
    }
}
