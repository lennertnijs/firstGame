package Clock;

import com.mygdx.game.Clock.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClockTest {

    @Test
    public void testClock(){
        Season dark = Season.DARK;
        Season light = Season.LIGHT;

        Calendar calendar = Calendar.builder()
                .put(dark, 13)
                .put(light, 1)
                .build();

        Clock clock = Clock.builder()
                .calendar(calendar)
                .season(dark)
                .dayOfTheSeason(12)
                .day(Day.SUNDAY)
                .timeInMinutes(1420)
                .build();

        Assertions.assertEquals("23:40", clock.getTimeInHHMM());

        clock.increaseTimeByMinutes(21);
        Assertions.assertEquals("00:01", clock.getTimeInHHMM());
        Assertions.assertEquals(clock.getDayOfTheSeason(),13);
        Assertions.assertEquals(clock.getSeason(), dark);
        Assertions.assertEquals(clock.getCalendar(), calendar);
        Assertions.assertEquals(clock.getTimeInMinutes(), 1);

        clock.increaseTimeByMinutes(1420);
        Assertions.assertEquals("23:41", clock.getTimeInHHMM());
        Assertions.assertEquals(clock.getDayOfTheSeason(),13);
        Assertions.assertEquals(clock.getSeason(), dark);
        Assertions.assertEquals(clock.getCalendar(), calendar);
        Assertions.assertEquals(clock.getTimeInMinutes(), 1421);

        clock.increaseTimeByMinutes(21);
        Assertions.assertEquals("00:02", clock.getTimeInHHMM());
        Assertions.assertEquals(clock.getDayOfTheSeason(),1);
        Assertions.assertEquals(clock.getSeason(), light);
        Assertions.assertEquals(clock.getCalendar(), calendar);
        Assertions.assertEquals(clock.getTimeInMinutes(), 2);

        clock.increaseTimeByMinutes(1438);
        Assertions.assertEquals("00:00", clock.getTimeInHHMM());
        Assertions.assertEquals(clock.getDayOfTheSeason(),1);
        Assertions.assertEquals(clock.getSeason(), dark);
        Assertions.assertEquals(clock.getCalendar(), calendar);
        Assertions.assertEquals(clock.getTimeInMinutes(), 0);

        clock.increaseTimeByMinutes(1440);
        Assertions.assertEquals("00:00", clock.getTimeInHHMM());
        Assertions.assertEquals(clock.getDayOfTheSeason(),2);
        Assertions.assertEquals(clock.getSeason(), dark);
        Assertions.assertEquals(clock.getCalendar(), calendar);
        Assertions.assertEquals(clock.getTimeInMinutes(), 0);
    }
}
