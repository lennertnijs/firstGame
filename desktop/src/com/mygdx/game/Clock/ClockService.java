package com.mygdx.game.Clock;

import static com.mygdx.game.Constants.MILLIS_PER_MINUTE;

public class ClockService {

    private final ClockRepository clockRepository;
    private long lastUpdateTimeInMillis;
    private long timeElapsedInMillis;

    public ClockService(){
        this.clockRepository = new ClockRepository();
    }

    public Clock getClock(){
        return clockRepository.getClock();
    }

    public void startClock(){
        lastUpdateTimeInMillis = System.currentTimeMillis();
        timeElapsedInMillis = 0;
        clockRepository.getClock().setActive(false);
    }

    public void pauseClock(){
        clockRepository.getClock().setActive(true);
    }

    /**
     * Updates the clock on every frame.
     * Uses the System.currentTimeMillis() to accurately get timeframes.
     * Everytime a second has passed, one minute is added to the clock.
     * Thus, the current clock takes 24 minutes to complete a day, aka 1 minute per hour.
     */
    public void updateClock(){
        boolean clockActive = clockRepository.getClock().getActive();
        if(clockActive){
            updateActiveClock();
        }
    }

    private void updateActiveClock(){
        long currentMillis = System.currentTimeMillis();
        long timeDifference = currentMillis - lastUpdateTimeInMillis;
        timeElapsedInMillis += timeDifference;
        if(timeElapsedInMillis >= MILLIS_PER_MINUTE){
            timeElapsedInMillis -= MILLIS_PER_MINUTE;
            clockRepository.getClock().incrementTimeByOne();
        }
        lastUpdateTimeInMillis = currentMillis;
    }
}
