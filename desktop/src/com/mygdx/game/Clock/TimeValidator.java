package com.mygdx.game.Clock;

import static com.mygdx.game.Constants.*;

public class TimeValidator {

    public static void validateNumberOfMinutesToAdd(int minutes){
        if(minutes < 0 ||  MINUTES_PER_DAY < minutes ){
            throw new IllegalArgumentException("Invalid time to add");
        }
    }
}
