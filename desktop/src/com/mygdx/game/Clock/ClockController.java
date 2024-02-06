package com.mygdx.game.Clock;

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
