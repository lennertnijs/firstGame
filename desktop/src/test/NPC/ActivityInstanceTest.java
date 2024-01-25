package NPC;

import com.mygdx.game.Constants;
import com.mygdx.game.Map.Map;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.NPC.ActivityInstance;
import com.mygdx.game.NPC.Position2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


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
                () -> Assertions.assertEquals(activityInstance.getPosition(), position),
                () -> Assertions.assertEquals(activityInstance.getActivity(), activity),
                () -> Assertions.assertEquals(activityInstance.getTimeInMinutes(), timeInMin),
                () -> Assertions.assertEquals(activityInstance.getMap(), map)
        );
    }

    @Test
    public void testActivityInstanceConstructorInvalid(){
        Position2D position = new Position2D(500, 500);
        Map map = Map.values()[0];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];

        ActivityInstance.Builder builder = ActivityInstance.builder()
                .position(position)
                .timeInMinutes(timeInMin)
                .map(map)
                .activity(activity);

        Assertions.assertThrows(NullPointerException.class, () -> builder.position(null).build());
        builder.position(position);
        Assertions.assertThrows(NullPointerException.class, () -> builder.map(null).build());
        builder.map(map);
        Assertions.assertThrows(NullPointerException.class, () -> builder.activity(null).build());
        builder.activity(activity);
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder.timeInMinutes(-1).build());
    }
}
