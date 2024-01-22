package com.mygdx.game.Clock;

import static com.mygdx.game.Constants.MAX_TIME_MINUTES;
import static com.mygdx.game.Constants.MIN_TIME_MINUTES;

public class TimeValidator {

    /**
     * The reason why the '+1'  is there is to be able to go forward 24 hours (0 minutes is equal to 00:00)
     */
    public static void validateTimeToAddInMinutes(int minutes){
        if(minutes < MIN_TIME_MINUTES ||  MAX_TIME_MINUTES + 1 < minutes ){
            throw new IllegalArgumentException("Invalid time to add");
        }
    }
}
