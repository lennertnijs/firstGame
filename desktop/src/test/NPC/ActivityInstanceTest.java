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
                .startTimeInMinutes(timeInMin)
                .activity(activity)
                .map(map)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(activityInstance.getPosition(), position),
                () -> Assertions.assertEquals(activityInstance.getStartTimeInMinutes(), timeInMin),
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
                                                            .startTimeInMinutes(timeInMin)
                                                            .map(map)
                                                            .activity(activity);

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.position(null).build()),
                () -> builder.position(position),

                () -> Assertions.assertThrows(IllegalArgumentException.class,() -> builder.startTimeInMinutes(-1).build()),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> builder.startTimeInMinutes(Constants.MINUTES_PER_DAY).build()),
                () -> builder.startTimeInMinutes(timeInMin),

                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.activity(null).build()),
                () -> builder.activity(activity),

                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.map(null).build())

        );
    }

    @Test
    public void testEquals(){
        ActivityInstance activity1 = generateValidActivityInstanceBuilder(200).build();
        ActivityInstance activity2 = generateValidActivityInstanceBuilder(100).build();
        ActivityInstance activity3 = generateValidActivityInstanceBuilder(200).build();

        Position position = Position.builder().x(200).y(200).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(activity1, activity3),
                () -> Assertions.assertNotEquals(activity1, activity2),
                () -> Assertions.assertNotEquals(activity1, position),
                () -> Assertions.assertEquals(activity1.hashCode(), activity3.hashCode())
        );
    }



    private ActivityInstance.Builder generateValidActivityInstanceBuilder(int x){
        Position position = Position.builder().x(x).y( 120).build();
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Map map = Map.values()[0];
        Activity activity = Activity.values()[0];

        return ActivityInstance.builder()
                .position(position)
                .startTimeInMinutes(timeInMin)
                .map(map)
                .activity(activity);
    }
}
