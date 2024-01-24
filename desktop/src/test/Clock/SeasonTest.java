package Clock;

import com.mygdx.game.Clock.Season;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeasonTest {

    @Test
    public void testSeason(){
        Season dark = Season.DARK;
        Season light = Season.LIGHT;
        Assertions.assertEquals(light, dark.next());
        Assertions.assertEquals(dark, light.next());
        Assertions.assertEquals(light.next().next(), light);
    }
}
