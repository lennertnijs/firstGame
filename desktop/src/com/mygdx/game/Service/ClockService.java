package com.mygdx.game.Service;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.DAO.ClockDAO;

public class ClockService {

    long lastUpdateInMillis;
    long timeElapsedInMillis;
    final Clock clock;

    public ClockService(){
        this.clock = loadClock();
        lastUpdateInMillis = System.currentTimeMillis();
        timeElapsedInMillis = 0;
    }

    public Clock loadClock(){
        ClockDAO clockDAO = new ClockDAO();
        return clockDAO.readClock();
    }

    public Clock getClock(){
        return clock;
    }

    /**
     * Updates the clock on every frame.
     * Uses the System.currentTimeMillis() to accurately get timeframes.
     * Everytime a second has passed, one minute is added to the clock.
     * Thus, the current clock takes 24 minutes to complete a day, aka 1 minute per hour.
     */
    public void updateClock(){
        long currentMillis = System.currentTimeMillis();
        long timeDifference = currentMillis - lastUpdateInMillis;
        timeElapsedInMillis += timeDifference;
        if(timeElapsedInMillis >= 1000){
            timeElapsedInMillis -= 1000;
            clock.incrementTimeByOne();
        }
        lastUpdateInMillis = currentMillis;
    }
}
