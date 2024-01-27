package NPC;

import com.mygdx.game.Clock.Day;
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
    public void testDayScheduleConstructor(){
        Day day = Day.values()[0];

        Position position = Position.builder().x(500).y( 500).build();
        Map map = Map.values()[0];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .timeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Position position2 = Position.builder().x(500).y( 500).build();
        Map map2 = Map.values()[0];
        int timeInMin2 = Constants.MINUTES_PER_DAY/2;
        Activity activity2 = Activity.values()[0];
        ActivityInstance activityInstance2 = ActivityInstance.builder()
                .position(position2)
                .timeInMinutes(timeInMin2)
                .map(map2)
                .activity(activity2)
                .build();

        ArrayList<ActivityInstance> activities = new ArrayList<>(Arrays.asList(activityInstance, activityInstance2));

        DaySchedule daySchedule = DaySchedule.builder()
                .day(day)
                .addActivity(activityInstance)
                .addActivity(activityInstance2)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(daySchedule.getDay(), day),
                () -> Assertions.assertEquals(daySchedule.getActivities().get(0),activityInstance),
                () -> Assertions.assertEquals(daySchedule.getActivities().get(1),activityInstance2),
                () -> Assertions.assertEquals(daySchedule.getActivities(), activities)
        );

        DaySchedule daySchedule2 = DaySchedule.builder()
                .day(day)
                .activities(activities)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(daySchedule2.getDay(), day),
                () -> Assertions.assertEquals(daySchedule2.getActivities().get(0),activityInstance),
                () -> Assertions.assertEquals(daySchedule2.getActivities().get(1),activityInstance2),
                () -> Assertions.assertEquals(daySchedule2.getActivities(), activities)
        );
    }

    @Test
    public void testDayScheduleConstructorInvalid(){
        Day day = Day.values()[0];
        ArrayList<ActivityInstance> activities = new ArrayList<>();

        DaySchedule.Builder builder = DaySchedule.builder().day(day).activities(activities);

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, ()->builder.day(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, ()->builder.activities(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, ()->builder.addActivity(null).build())
        );
    }

    @Test
    public void testDayScheduleNext(){
        Day day = Day.values()[0];

        Position position = Position.builder().x(500).y( 500).build();
        Map map = Map.values()[0];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .timeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Position position2 = Position.builder().x(750).y( 750).build();
        Map map2 = Map.values()[0];
        int timeInMin2 = Constants.MINUTES_PER_DAY/2;
        Activity activity2 = Activity.values()[0];
        ActivityInstance activityInstance2 = ActivityInstance.builder()
                .position(position2)
                .timeInMinutes(timeInMin2)
                .map(map2)
                .activity(activity2)
                .build();

        Position position3 = Position.builder().x(250).y( 250).build();
        Map map3 = Map.values()[0];
        int timeInMin3 = Constants.MINUTES_PER_DAY/2;
        Activity activity3 = Activity.values()[0];
        ActivityInstance activityInstance3 = ActivityInstance.builder()
                .position(position3)
                .timeInMinutes(timeInMin3)
                .map(map3)
                .activity(activity3)
                .build();

        DaySchedule daySchedule = DaySchedule.builder()
                .day(day)
                .addActivity(activityInstance)
                .addActivity(activityInstance2)
                .addActivity(activityInstance3)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(daySchedule.next(activityInstance), activityInstance2),
                () -> Assertions.assertEquals(daySchedule.next(activityInstance2), activityInstance3),
                () -> Assertions.assertNull(daySchedule.next(activityInstance3))
        );
    }

    @Test
    public void testDayScheduleNextInvalid(){
        Position position = Position.builder().x(500).y( 500).build();
        Map map = Map.values()[0];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .timeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        DaySchedule daySchedule = DaySchedule.builder().day(Day.values()[0]).activities(new ArrayList<>()).build();

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> daySchedule.next(null)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> daySchedule.next(activityInstance))
        );
    }
}
