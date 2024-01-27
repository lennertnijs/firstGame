package com.mygdx.game.Controller;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.Service.ClockService;

public class ClockController {

    private final ClockService clockService;

    public ClockController(ClockService clockService){
        this.clockService = clockService;
    }

    public void updateClock(){
        clockService.updateClock();
    }

    public void loadClock(){
        clockService.loadClock();
    }

    public Clock getClock(){
        return clockService.getClock();
    }
}
