package com.mygdx.game.Clock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Headless.HeadlessApplication;
import com.mygdx.game.Headless.HeadlessApplicationConfiguration;
import com.mygdx.game.MockGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;

public class ClockServiceTest {

    private ClockService clockService;

    @BeforeEach
    public void initialiseHeadless(){
        HeadlessApplication headlessApp = new HeadlessApplication(
                new MockGame(), new HeadlessApplicationConfiguration());
        Gdx.gl = mock(GL20.class);
        clockService = new ClockService();
    }

    @Test
    public void testClockService(){
        Map<Season, Integer> seasonLengths = new HashMap<Season, Integer>(){{
            put(Season.LIGHT, 28);
            put(Season.DARK, 28);
        }};
        Calendar calendar = Calendar.builder().seasons(seasonLengths).build();
        Season season = Season.DARK;
        Day day = Day.MONDAY;
        Clock clock = Clock.builder().calendar(calendar).season(season).day(day).dayOfTheSeason(1).timeInMinutes(510).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(clockService.getClock(), clock)
        );
    }


    @Test
    public void pauseClockTest(){
        clockService.pauseClock();
        Assertions.assertAll(
                () -> Assertions.assertFalse(clockService.getClock().getActive())
        );
    }



    @Test
    public void startClockTest(){
        clockService.pauseClock();
        Assertions.assertAll(
                () -> clockService.startClock(),
                () -> Assertions.assertTrue(clockService.getClock().getActive())
        );
    }




    @Test
    public void updateClockTest() throws InterruptedException {
        int clockTime = clockService.getClock().getTimeInMinutes();
        TimeUnit.SECONDS.sleep(1);
        clockService.updateClock();
        Assertions.assertAll(
                () -> Assertions.assertEquals(clockService.getClock().getTimeInMinutes(), clockTime+1)
        );
    }
}
