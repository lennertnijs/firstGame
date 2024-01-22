package Clock;

import com.mygdx.game.Clock.Calendar;
import com.mygdx.game.Clock.Season;
import com.mygdx.game.Clock.SeasonName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalendarTest {

    @Test
    public void testCalendar(){
        Calendar calendar = new Calendar();

        SeasonName name = SeasonName.DARK;
        Season s = Season.builder().seasonName(name).lengthInDays(28).build();
        Season s2 = Season.builder().seasonName(SeasonName.LIGHT).lengthInDays(28).build();
        calendar.addSeason(s);

        Assertions.assertEquals(calendar.size(), 1);
        Assertions.assertTrue(calendar.containsSeason(s.getSeasonName()));
        Assertions.assertEquals(calendar.getSeasonByName(SeasonName.DARK), s);

        Assertions.assertNull(calendar.getSeasonByName(SeasonName.LIGHT));
        calendar.addSeason(s2);

        Assertions.assertEquals(calendar.getSeasonByName(SeasonName.LIGHT), s2);

        Assertions.assertThrows(IllegalArgumentException.class, ()-> calendar.containsSeason(null));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> calendar.getSeasonByName(null));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> calendar.addSeason(null));
    }
}
