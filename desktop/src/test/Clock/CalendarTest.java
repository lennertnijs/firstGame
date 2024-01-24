package Clock;

import com.mygdx.game.Clock.Calendar;
import com.mygdx.game.Clock.Season;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalendarTest {

    @Test
    public void testCalendar(){
        Season dark = Season.DARK;
        Season light = Season.LIGHT;
        Calendar calendar = Calendar.builder().put(dark, 28).put(light, 10).build();

        Assertions.assertEquals(calendar.size(), 2);

        Assertions.assertEquals(calendar.getSeasonLength(dark), 28);
        Assertions.assertEquals(calendar.getSeasonLength(light), 10);

        Assertions.assertThrows(IllegalArgumentException.class, ()-> calendar.getSeasonLength(null));
    }
}
