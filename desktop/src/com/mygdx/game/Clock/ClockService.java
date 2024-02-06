package com.mygdx.game.Clock;

import static com.mygdx.game.Constants.MILLIS_PER_MINUTE;

public class ClockService {

    private final ClockRepository clockRepository;
    private long lastUpdateTimeInMillis;
    private long timeElapsedInMillis;

    /**
     * The constructor for the {@code ClockService}.
     * It contains all the necessary {@code Clock} business logic.
     * Automatically generates a {@code ClockRepository} and reads clock.json for a valid {@code Clock}.
     */
    public ClockService(){
        this.clockRepository = new ClockRepository();
    }

    /**
     * Fetches and returns the active {@code Clock}
     * @return The active {@code Clock}
     */
    public Clock getClock(){
        return clockRepository.getClock();
    }

    /**
     * Starts the {@code Clock} up.
     */
    public void startClock(){
        if(clockRepository.getClock().getActive()){
            return;
        }
        lastUpdateTimeInMillis = System.currentTimeMillis();
        timeElapsedInMillis = 0;
        clockRepository.getClock().setActive(true);
    }

    /**
     * Pauses the {@code Clock}.
     */
    public void pauseClock(){
        clockRepository.getClock().setActive(false);
    }

    /**
     * Handles the update of the clock.
     * If the {@code Clock} is paused, does nothing.
     */
    public void updateClock(){
        boolean clockActive = clockRepository.getClock().getActive();
        if(clockActive){
            updateActiveClock();
        }
    }

    /**
     * Handles the update of the active {@code Clock}.
     * Uses the {@code System.currentTimeMillis()}.
     * Increments the {@code Clock}'s time by one every {@code MILLIS_PER_MINUTE}.
     */
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
