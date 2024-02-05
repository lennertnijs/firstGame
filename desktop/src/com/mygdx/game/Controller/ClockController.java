package com.mygdx.game.Controller;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.Clock.ClockService;

public class ClockController {

    private final ClockService clockService;

    public ClockController(ClockService clockService){
        this.clockService = clockService;
    }

    public Clock getClock(){
        return clockService.getClock();
    }
}
