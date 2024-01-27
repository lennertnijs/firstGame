package Clock;

import com.mygdx.game.Clock.*;
import com.mygdx.game.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ClockTest {

    @Test
    public void testConstructor(){
        Season one = Season.values()[0];
        Season two = Season.values()[1];

        HashMap<Season, Integer> seasons = new HashMap<Season, Integer>(){{
            put(one, 28);
            put(two, 10);
        }};

        Calendar calendar = Calendar.builder()
                .seasons(seasons)
                .build();

        Clock clock = Clock.builder()
                .calendar(calendar)
                .season(one)
                .dayOfTheSeason(28)
                .day(Day.values()[0])
                .timeInMinutes(1439)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(clock.getDay(), Day.values()[0]),
                () -> Assertions.assertEquals(clock.getDayOfTheSeason(),28),
                () -> Assertions.assertEquals(clock.getSeason(), one),
                () -> Assertions.assertEquals(clock.getCalendar(), calendar),
                () -> Assertions.assertEquals(clock.getTimeInMinutes(), 1439)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Clock.Builder builder1 = generateValidClockBuilder();
        Clock.Builder builder2 = generateValidClockBuilder();
        Clock.Builder builder3 = generateValidClockBuilder();
        Clock.Builder builder4 = generateValidClockBuilder();
        Clock.Builder builder5 = generateValidClockBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder1.calendar(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder2.day(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder3.season(null).build()),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder4.dayOfTheSeason(0).build()),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder4.dayOfTheSeason(29).build()),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder5.timeInMinutes(-1).build()),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> builder5.timeInMinutes(Constants.MINUTES_PER_DAY).build())
        );
    }

    @Test
    public void testGetSeasonLength(){
        Clock clock = generateValidClockBuilder().build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(clock.getSeasonLength(), 28)
        );
    }

    @Test
    public void testClockIncrements(){
        Season two = Season.values()[1];
        Clock clock = generateValidClockBuilder().build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(clock.getTimeInHHMM(), "23:59"),
                () -> clock.incrementTimeByOne(),

                () -> Assertions.assertEquals(clock.getTimeInHHMM(), "00:00"),
                () -> Assertions.assertEquals(clock.getSeason(), two),
                () -> Assertions.assertEquals(clock.getDayOfTheSeason(), 1),
                () -> Assertions.assertEquals(clock.getDay(), Day.values()[0].next()),

                () -> clock.increaseTimeByMinutes(Constants.MINUTES_PER_DAY),
                () -> Assertions.assertEquals(clock.getTimeInHHMM(), "00:00"),
                () -> Assertions.assertEquals(clock.getSeason(), two),
                () -> Assertions.assertEquals(clock.getDayOfTheSeason(), 2),
                () -> Assertions.assertEquals(clock.getDay(), Day.values()[0].next().next()),

                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> clock.increaseTimeByMinutes(-1)),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> clock.increaseTimeByMinutes(Constants.MINUTES_PER_DAY + 1))
        );
    }

    private Clock.Builder generateValidClockBuilder(){
        Season one = Season.values()[0];
        Season two = Season.values()[1];

        HashMap<Season, Integer> seasons = new HashMap<Season, Integer>(){{
            put(one, 28);
            put(two, 10);
        }};

        Calendar calendar = Calendar.builder()
                .seasons(seasons)
                .build();

        return Clock.builder()
                .calendar(calendar)
                .season(one)
                .dayOfTheSeason(28)
                .day(Day.values()[0])
                .timeInMinutes(1439);
    }
}
