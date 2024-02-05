package com.mygdx.game.Clock;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.Clock.ClockRepository;
import com.mygdx.game.DAO.ClockDAO;

public class ClockService {

    long lastUpdateInMillis;
    long timeElapsedInMillis;
    private final ClockRepository clockRepository;

    public ClockService(){
        this.clockRepository = new ClockRepository();
        lastUpdateInMillis = System.currentTimeMillis();
        timeElapsedInMillis = 0;
    }

    public Clock getClock(){
        return clockRepository.getClock();
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
            clockRepository.getClock().incrementTimeByOne();
        }
        lastUpdateInMillis = currentMillis;
    }
}
