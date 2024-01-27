package Clock;

import com.mygdx.game.Clock.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DayTest {

    @Test
    public void testDay(){
        for(int i = 0; i < Day.values().length; i++){
            int index = (i+1)%Day.values().length;
            Day next = Day.values()[index];
            Assertions.assertEquals(Day.values()[i].next(), next);
        }
    }
}
