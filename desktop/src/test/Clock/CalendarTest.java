package Clock;

import com.mygdx.game.Clock.Calendar;
import com.mygdx.game.Clock.Season;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class CalendarTest {

    @Test
    public void testCalendar(){
        Season one = Season.values()[0];
        Season two = Season.values()[1];
        HashMap<Season, Integer> seasons = new HashMap<Season, Integer>(){{
            put(one, 28);
            put(two, 10);
        }};

        Calendar calendar = Calendar.builder().seasons(seasons).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(calendar.getSeasons(), seasons)
        );
    }

    @Test
    public void testCalendarInvalid(){
        Calendar.Builder builder = Calendar.builder();

        Season one = Season.values()[0];
        Season two = Season.values()[1];
        HashMap<Season, Integer> seasons = new HashMap<Season, Integer>(){{
            put(one, 28);
            put(two, 10);
        }};

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.seasons(null).build()),

                () -> seasons.put(one, null),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.seasons(seasons).build()),

                () -> seasons.put(one, -1),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder.seasons(seasons).build()),

                () -> seasons.put(null, 10),
                () -> seasons.put(one, 5),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.seasons(seasons).build())
        );
    }

    @Test
    public void testAmountOfSeasons(){
        Season one = Season.values()[0];
        Season two = Season.values()[1];
        HashMap<Season, Integer> seasons = new HashMap<Season, Integer>(){{
            put(one, 28);
            put(two, 10);
        }};

        Calendar calendar = Calendar.builder().seasons(seasons).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(calendar.amountOfSeasons(), 2)
        );
    }

    @Test
    public void testGetSeasonLength(){
        Season one = Season.values()[0];
        Season two = Season.values()[1];
        HashMap<Season, Integer> seasons = new HashMap<Season, Integer>(){{
            put(one, 28);
        }};

        Calendar calendar = Calendar.builder().seasons(seasons).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(calendar.getSeasonLength(one), 28),
                () -> Assertions.assertThrows(NullPointerException.class, () -> calendar.getSeasonLength(two))
        );
    }

    @Test
    public void testGetSeasonLengthInvalid(){
        Season one = Season.values()[0];
        HashMap<Season, Integer> seasons = new HashMap<Season, Integer>(){{
            put(one, 28);
        }};

        Calendar calendar = Calendar.builder().seasons(seasons).build();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> calendar.getSeasonLength(null))
        );
    }

    @Test
    public void testEquals(){
        Season one = Season.values()[0];
        Season two = Season.values()[1];
        HashMap<Season, Integer> seasons1 = new HashMap<Season, Integer>(){{
            put(one, 28);
            put(two, 10);
        }};

        HashMap<Season, Integer> seasons2 = new HashMap<Season, Integer>(){{
            put(one, 28);
            put(two, 10);
        }};

        HashMap<Season, Integer> seasons3 = new HashMap<Season, Integer>(){{
            put(two, 28);
        }};

        Assertions.assertAll(
                () -> Assertions.assertEquals(seasons1, seasons2),
                () -> Assertions.assertNotEquals(seasons1, seasons3),
                () -> Assertions.assertNotEquals(seasons1, null),
                () -> Assertions.assertNotEquals(seasons1, new ArrayList<>()),
                () -> Assertions.assertEquals(seasons1.hashCode(), seasons2.hashCode()),
                () -> Assertions.assertNotEquals(seasons1.hashCode(), seasons3.hashCode())
        );
    }
}
