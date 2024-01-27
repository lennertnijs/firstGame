package NPC;

import com.mygdx.game.Constants;
import com.mygdx.game.Map.Map;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.NPC.ActivityInstance;
import com.mygdx.game.NPC.DaySchedule;
import com.mygdx.game.Entity.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DayScheduleTest {

    @Test
    public void testConstructor(){
        Position position = Position.builder().x(500).y( 500).build();
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];
        Map map = Map.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .startTimeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Position position2 = Position.builder().x(500).y( 500).build();
        int timeInMin2 = Constants.MINUTES_PER_DAY/2;
        Activity activity2 = Activity.values()[0];
        Map map2 = Map.values()[0];
        ActivityInstance activityInstance2 = ActivityInstance.builder()
                .position(position2)
                .startTimeInMinutes(timeInMin2)
                .map(map2)
                .activity(activity2)
                .build();

        ArrayList<ActivityInstance> activities = new ArrayList<>(Arrays.asList(activityInstance, activityInstance2));

        DaySchedule daySchedule = DaySchedule.builder()
                .activities(activities)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(activities, daySchedule.getActivities()),
                () -> Assertions.assertEquals(daySchedule.getActivities().get(0),activityInstance),
                () -> Assertions.assertEquals(daySchedule.getActivities().get(1),activityInstance2)
        );
    }

    @Test
    public void testConstructorInvalid(){
        ArrayList<ActivityInstance> activities = new ArrayList<>();

        DaySchedule.Builder builder = DaySchedule.builder().activities(activities);

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, ()->builder.activities(null).build()),
                () -> builder.activities(activities),

                () -> activities.add(null),
                () -> Assertions.assertThrows(NullPointerException.class, ()->builder.activities(activities).build())
        );
    }

    @Test
    public void testNextActivityAfterTime(){
        DaySchedule daySchedule = generateValidActivityInstanceBuilder().build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(daySchedule.nextActivityAfterTime(0), daySchedule.getActivities().get(0)),

                () -> Assertions.assertEquals(daySchedule.nextActivityAfterTime(Constants.MINUTES_PER_DAY/2),
                        daySchedule.getActivities().get(1)),

                () -> Assertions.assertNull(daySchedule.nextActivityAfterTime(Constants.MINUTES_PER_DAY - 1))
        );
    }

    @Test
    public void testNextActivityAfterTimeInvalid(){
        DaySchedule daySchedule = generateValidActivityInstanceBuilder().build();
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> daySchedule.nextActivityAfterTime(-1)),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> daySchedule.nextActivityAfterTime(Constants.MINUTES_PER_DAY))
        );
    }

    @Test
    public void testEquals(){
        DaySchedule daySchedule = generateValidActivityInstanceBuilder().build();
        DaySchedule daySchedule1 = generateValidActivityInstanceBuilder().build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(daySchedule, daySchedule1)
        );
    }

    private DaySchedule.Builder generateValidActivityInstanceBuilder(){
        Position position = Position.builder().x(500).y( 500).build();
        int timeInMin = 0;
        Activity activity = Activity.values()[0];
        Map map = Map.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .startTimeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Position position2 = Position.builder().x(500).y( 500).build();
        int timeInMin2 = Constants.MINUTES_PER_DAY/2;
        Activity activity2 = Activity.values()[0];
        Map map2 = Map.values()[0];
        ActivityInstance activityInstance2 = ActivityInstance.builder()
                .position(position2)
                .startTimeInMinutes(timeInMin2)
                .map(map2)
                .activity(activity2)
                .build();

        ArrayList<ActivityInstance> activities = new ArrayList<>(Arrays.asList(activityInstance, activityInstance2));

        return DaySchedule.builder().activities(activities);
    }

}
