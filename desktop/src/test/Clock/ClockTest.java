package Clock;

import com.mygdx.game.Clock.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClockTest {

    @Test
    public void testClock(){
        int minutes = 1420;
        int currentDaysInMonth = 12;
        Day day = Day.builder().dayName(DayName.SUNDAY).build();
        Season dark = Season.builder().seasonName(SeasonName.DARK).lengthInDays(13).build();
        Season light = Season.builder().seasonName(SeasonName.LIGHT).lengthInDays(1).build();
        Calendar calendar = new Calendar();
        calendar.addSeason(dark);
        calendar.addSeason(light);
        Clock clock = Clock.builder().calendar(calendar).currentSeason(dark).dayInCurrentSeason(currentDaysInMonth)
                .day(day).timeInMinutes(minutes).build();
        Assertions.assertEquals("23:40", clock.getTimeInHHMM());

        clock.increaseTimeByMinutes(21);
        Assertions.assertEquals("00:01", clock.getTimeInHHMM());
        Assertions.assertEquals(clock.getDayInSeason(),13);
        Assertions.assertEquals(clock.getSeason(), dark);
        Assertions.assertEquals(clock.getCalendar(), calendar);

        clock.increaseTimeByMinutes(1420);
        Assertions.assertEquals("23:41", clock.getTimeInHHMM());
        Assertions.assertEquals(clock.getDayInSeason(),13);
        Assertions.assertEquals(clock.getSeason(), dark);
        Assertions.assertEquals(clock.getCalendar(), calendar);

        clock.increaseTimeByMinutes(21);
        Assertions.assertEquals("00:02", clock.getTimeInHHMM());
        Assertions.assertEquals(clock.getDayInSeason(),1);
        Assertions.assertEquals(clock.getSeason(), light);
        Assertions.assertEquals(clock.getCalendar(), calendar);

        clock.increaseTimeByMinutes(1438);
        Assertions.assertEquals("00:00", clock.getTimeInHHMM());
        Assertions.assertEquals(clock.getDayInSeason(),1);
        Assertions.assertEquals(clock.getSeason(), dark);
        Assertions.assertEquals(clock.getCalendar(), calendar);
    }
}
