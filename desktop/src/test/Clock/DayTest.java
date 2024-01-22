package Clock;

import com.mygdx.game.Clock.Day;
import com.mygdx.game.Clock.DayName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.mygdx.game.Constants.HOURS_PER_DAY;

public class DayTest {

    @Test
    public void testDay(){
        DayName dayName = DayName.SUNDAY;
        Day day = Day.builder().dayName(dayName).build();
        Assertions.assertEquals(day.getLengthInHours(), HOURS_PER_DAY);
        Assertions.assertEquals(day.getDayName(), DayName.SUNDAY);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> Day.builder().dayName(null).build());
    }
}
