package NPC;

import com.mygdx.game.Constants;
import com.mygdx.game.Map.Map;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.NPC.ActivityInstance;
import com.mygdx.game.NPC.Position2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ActivityInstanceTest {

    @Test
    public void testActivityInstanceConstructor(){
        Position2D position = new Position2D(500, 500);
        Map map = Map.values()[0];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];

        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .timeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Assertions.assertAll(
                () -> assertEquals( activityInstance.getPosition(), position),
                () -> assertEquals(activityInstance.getActivity(), activity),
                () -> assertEquals(activityInstance.getTimeInMinutes(), timeInMin),
                () -> assertEquals(activityInstance.getMap(), map)
        );
    }
}
