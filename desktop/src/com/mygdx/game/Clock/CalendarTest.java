package com.mygdx.game.Clock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarTest {

    private Season season1;
    private Season season2;
    private Calendar calendar;

    @BeforeEach
    public void initialise(){
        season1 = Season.create(SeasonName.values()[0], 28);
        season2 = Season.create(SeasonName.values()[1], 30);
        calendar = Calendar.create(new ArrayList<>(Arrays.asList(season1, season2)));
    }


    @Test
    public void testCalendar(){
        Assertions.assertAll(
                () -> Assertions.assertEquals(calendar.getSeasons(), new ArrayList<>(Arrays.asList(season1, season2)))
        );
    }


    @Test
    public void testAmountOfSeasons(){
        Assertions.assertAll(
                () -> Assertions.assertEquals(calendar.getAmountOfSeasons(), 2)
        );
    }


    @Test
    public void testGetSeasonLength(){
        Assertions.assertAll(
                () -> Assertions.assertEquals(calendar.getSeasonLength(season1.getName()), 28)
        );
    }
}
