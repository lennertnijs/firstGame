package com.mygdx.game.Clock;

public class ClockController {

    long lastUpdateInMillis;
    long timeElapsedInMillis;
    public ClockController(){
        lastUpdateInMillis = System.currentTimeMillis();
        timeElapsedInMillis = 0;
    }

    /**
     * Updates the clock on every frame.
     * Uses the System.currentTimeMillis() to accurately get timeframes.
     * Everytime a second has passed, one minute is added to the clock.
     * Thus, the current clock takes 24 minutes to complete a day, aka 1 minute per hour.
     */
    public void updateClock(Clock clock){
        long currentMillis = System.currentTimeMillis();
        long timeDifference = currentMillis - lastUpdateInMillis;
        timeElapsedInMillis += timeDifference;
        if(timeElapsedInMillis >= 1000){
            timeElapsedInMillis -= 1000;
            clock.increaseTimeByMinutes(1);
        }
        lastUpdateInMillis = currentMillis;
    }
}
