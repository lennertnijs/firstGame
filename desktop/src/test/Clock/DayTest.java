package Clock;

import com.mygdx.game.Clock.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DayTest {

    @Test
    public void testDay(){
        for(int i = 0; i < Day.values().length; i++){
            Day currentDay = Day.values()[i];
            int nextIndex = (i + 1) % Day.values().length;
            Day nextDay = Day.values()[nextIndex];
            Assertions.assertEquals(currentDay.next(), nextDay);
        }
    }
}
