package Clock;

import com.mygdx.game.Clock.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DayTest {

    @Test
    public void testDay(){
        Day monday = Day.MONDAY;
        Day tuesday = Day.TUESDAY;
        Day wednesday = Day.WEDNESDAY;
        Day thursday = Day.THURSDAY;
        Day friday = Day.FRIDAY;
        Day saturday = Day.SATURDAY;
        Day sunday = Day.SUNDAY;
        Assertions.assertEquals(monday.next(), Day.TUESDAY);
        Assertions.assertEquals(tuesday.next(), Day.WEDNESDAY);
        Assertions.assertEquals(wednesday.next(), Day.THURSDAY);
        Assertions.assertEquals(thursday.next(), Day.FRIDAY);
        Assertions.assertEquals(friday.next(), Day.SATURDAY);
        Assertions.assertEquals(saturday.next(), Day.SUNDAY);
        Assertions.assertEquals(sunday.next(), Day.MONDAY);

        Assertions.assertEquals(monday.next().next().next().next().next().next().next(), Day.MONDAY);
    }
}
