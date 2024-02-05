package com.mygdx.game.Clock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeasonTest {

    @Test
    public void testSeason(){
        for(int i = 0; i < Season.values().length; i++){
            int index = (i+1)%Season.values().length;
            Season next = Season.values()[index];
            Assertions.assertEquals(next, Season.values()[i].next());
        }
    }
}
