package NPC;

import com.mygdx.game.Constants;
import com.mygdx.game.Map.Map;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.NPC.ActivityInstance;
import com.mygdx.game.Entity.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ActivityInstanceTest {

    @Test
    public void testConstructor(){
        Position position = Position.builder().x(200).y( 120).build();
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];
        Map map = Map.values()[0];

        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .timeInMinutes(timeInMin)
                .activity(activity)
                .map(map)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(activityInstance.getPosition(), position),
                () -> Assertions.assertEquals(activityInstance.getTimeInMinutes(), timeInMin),
                () -> Assertions.assertEquals(activityInstance.getActivity(), activity),
                () -> Assertions.assertEquals(activityInstance.getMap(), map)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Position position = Position.builder().x(200).y( 120).build();
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Map map = Map.values()[0];
        Activity activity = Activity.values()[0];

        ActivityInstance.Builder builder = ActivityInstance.builder()
                                                            .position(position)
                                                            .timeInMinutes(timeInMin)
                                                            .map(map)
                                                            .activity(activity);

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.position(null).build()),
                () -> builder.position(position),

                () -> Assertions.assertThrows(IllegalArgumentException.class,() -> builder.timeInMinutes(-1).build()),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> builder.timeInMinutes(Constants.MINUTES_PER_DAY).build()),
                () -> builder.timeInMinutes(timeInMin),

                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.activity(null).build()),
                () -> builder.activity(activity),

                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.map(null).build())

        );
    }
}
