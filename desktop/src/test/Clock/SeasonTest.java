package Clock;

import com.mygdx.game.Clock.Season;
import com.mygdx.game.Clock.SeasonName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeasonTest {

    @Test
    public void testSeason(){
        SeasonName name = SeasonName.DARK;
        Season s = Season.builder().seasonName(name).lengthInDays(28).build();
        Assertions.assertEquals(s.getSeasonName(), SeasonName.DARK);
        Assertions.assertEquals(s.getLengthInDays(), 28);

        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> Season.builder().seasonName(null).lengthInDays(28).build());

        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> Season.builder().seasonName(name).lengthInDays(-1).build());
    }
}
