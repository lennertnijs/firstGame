package com.mygdx.game.Clock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeasonTest {

    @Test
    public void testSeason(){
        for(int i = 0; i < Season.values().length; i++){
            Season currentSeason = Season.values()[i];
            int nextIndex = (i + 1) % Season.values().length;
            Season nextSeason = Season.values()[nextIndex];
            Assertions.assertEquals(currentSeason.next(), nextSeason);
        }
    }
}
