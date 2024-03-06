package Clock;

import com.mygdx.game.Clock.SeasonName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeasonNameTest {

    @Test
    public void testSeasonNext(){
        for(int i = 0; i < SeasonName.values().length; i++){
            SeasonName currentSeasonName = SeasonName.values()[i];
            int nextIndex = (i + 1) % SeasonName.values().length;
            SeasonName nextSeasonName = SeasonName.values()[nextIndex];
            Assertions.assertEquals(currentSeasonName.next(), nextSeasonName);
        }
    }
}
